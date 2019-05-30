package com.a3.bfd.controller;


import com.a3.bfd.handlers.*;
import com.a3.bfd.mediator.Mediator;
import com.a3.bfd.mediator.MediatorImpl;
import com.a3.bfd.model.StaffAccount;
import com.a3.bfd.model.UserAccount;
import com.a3.bfd.readService.OrderServiceR;
import com.a3.bfd.readService.ProductServiceR;
import com.a3.bfd.readService.StaffServiceR;
import com.a3.bfd.readService.UserServiceR;
import com.a3.bfd.writeService.OrderServiceW;
import com.a3.bfd.writeService.StaffServiceW;
import com.a3.bfd.writeService.UserServiceW;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import com.a3.bfd.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserControllerPost {
    @Autowired
    private UserServiceW userServiceW;
    @Autowired
    private UserServiceR userServiceR;
    @Autowired
    private StaffServiceW staffServiceW;
    @Autowired
    private StaffServiceR staffServiceR;

    @Autowired
    private OrderServiceR orderServiceR;
    @Autowired
    private OrderServiceW orderServiceW;

    @Autowired
    private ProductServiceR productServiceR;

    @Autowired
    private AddUserHandler addUserHandler;
    @Autowired
    private AddStaffHandler addStaffHandler;
    @Autowired
    private AddOrderHandler addOrderHandler;



    @PostMapping("/register")
    public String getData(ServletWebRequest request, @Valid @ModelAttribute("userAccount") UserAccount userAccount, BindingResult result,  Model model) {
        if (result.hasErrors()) {
            return "/logorreg";
        }
        if (userServiceR.findByUsername(userAccount.getUsername()) == null && staffServiceR.findByUsename(userAccount.getUsername()) == null) {
            if (request.getParameterValues("user") != null) {
                Mediator mediator=new MediatorImpl();
                Request request1=new AddUserHandler();
                addUserHandler.setUser(userAccount);
                mediator.handle(request1);
                return "/hello";
            } else {
                StaffAccount staffAccount = new StaffAccount(userAccount.getName(), userAccount.getAge(), userAccount.getAddress(), userAccount.getUsername(), userAccount.getPassword());
                Mediator mediator=new MediatorImpl();
                Request request1=new AddStaffHandler();
                addStaffHandler.setStaff(staffAccount);
                mediator.handle(request1);
                model.addAttribute("products", productServiceR.getAllProducts());
                model.addAttribute("orders", orderServiceR.getAllOrders());
                return "/staffPage";
            }

        }
        return "/tt";
    }
    @PostMapping("/order")
    public String placeOrder(@Valid @ModelAttribute("cart") Order cart, BindingResult result) {

        UserAccount userAccount = userServiceR.findByUsername(cart.getUsername());
        if (cart != null && cart.getUsername().equals(userAccount.getUsername()) && cart.getPassword().equals(userAccount.getPassword())) {
            Mediator mediator=new MediatorImpl();
            Request request1=new AddOrderHandler();
            addOrderHandler.setOrder(cart);
            mediator.handle(request1);
            return "/order";
        }
        return "/error";
    }
    @PostMapping(path = "/loginStaff")
    public String logg( @Valid @ModelAttribute("cart")Cart cart, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("products", productServiceR.getAllProducts() );
            return "/furniture";
        }
        if(userServiceR.findByUsername(cart.getUsername())!=null && userServiceR.findByUsername(cart.getUsername()).getPassword().equalsIgnoreCase(cart.getPassword()))
        {
            List<Order> orders= (List<Order>) orderServiceR.getAllOrders();
            model.addAttribute("history", orders.stream().filter(order -> order.getUsername().equalsIgnoreCase(cart.getUsername())).filter(order -> order.getState().equalsIgnoreCase("paid")).collect(Collectors.toList()));
            model.addAttribute("invoice", orders.stream().filter(order -> order.getUsername().equalsIgnoreCase(cart.getUsername())).filter(order -> order.getState().equalsIgnoreCase("delivering")).collect(Collectors.toList()));
            List<Order> orders1=orders.stream().filter(order -> order.getState().equalsIgnoreCase("delivering")).filter(order -> order.getUsername().equalsIgnoreCase(cart.getUsername())).collect(Collectors.toList());
            float price=0.0f;
            List<Product> list= (List<Product>) productServiceR.getAllProducts();
            for(Order order:orders1){
                for(Product p:list){
                    if(p.getId().equals(order.getIdProduct())){
                        price+=order.getAmountOrdered()*p.getPrice();
                    }
                }
            }
            System.out.println(cart.getUsername()+"  "+price);
            return "/invoice";
        }
        return "/tt";
    }

}
