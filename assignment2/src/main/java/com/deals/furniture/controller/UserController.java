package com.deals.furniture.controller;


import com.deals.furniture.model.*;
import com.deals.furniture.service.OrderService;
import com.deals.furniture.service.ProductService;
import com.deals.furniture.service.StaffService;
import com.deals.furniture.service.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userAccountRepository;
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private StaffService staffService;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public String showAll(Model model) {
        //templateResolver.
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("productId", productRepository.findAll());
        return "/furniture";
    }

    @GetMapping("/register")
    public String getPage(Model model){
        model.addAttribute("userAccount", new UserAccount());
        return "register";
    }

    @PostMapping("/register")
    public String getData(ServletWebRequest request, @Valid @ModelAttribute("userAccount") UserAccount userAccount, BindingResult result){
        if(result.hasErrors()){
            return "/logorreg";
        }
       if(userAccountRepository.findByUsername(userAccount.getUsername())==null && staffService.findByUsename(userAccount.getUsername())==null) {
           if (request.getParameterValues("user") != null) {

               userAccountRepository.addUser(userAccount);
               return "/hello";
           } else {
               StaffAccount staffAccount = new StaffAccount(userAccount.getName(), userAccount.getAge(), userAccount.getAddress(), userAccount.getUsername(), userAccount.getPassword());
               staffService.addUser(staffAccount);
               return "/staffPage/all";
           }
       }
       return "/tt";

    }
    @GetMapping("/order")
    public String order(Model model){
        model.addAttribute("cart", new Order());
        return "order";
    }
    @GetMapping("/error")
    public String error(){
        return "error";
    }


   @PostMapping("/order")
   public String placeOrder(@Valid @ModelAttribute("cart")Order cart, BindingResult result) {

       UserAccount userAccount = userAccountRepository.findByUsername(cart.getUsername());
       if (cart != null && cart.getUsername().equals(userAccount.getUsername()) && cart.getPassword().equals(userAccount.getPassword())) {
           orderService.addOrder(cart);
           return "/order";
       }
       return "/error";
   }
   Cart carts=null;
    @GetMapping(path="/invoice")
    public String invoice(Model model) {
        List<Order> orders= (List<Order>) orderService.getAllOrders();

        model.addAttribute("history", orders.stream().filter(order -> order.getUsername().equalsIgnoreCase(carts.getUsername())).filter(order -> order.getState().equalsIgnoreCase("paid")).collect(Collectors.toList()));
        model.addAttribute("invoice", orders.stream().filter(order -> order.getUsername().equalsIgnoreCase(carts.getUsername())).filter(order -> order.getState().equalsIgnoreCase("delivering")).collect(Collectors.toList()));

        return "/invoice";
    }

    @GetMapping(path = "/loginStaff")
    public String loginStaff(){
        return "/loginStaff";
    }

    @PostMapping(path = "/loginStaff")
    public String logg(@Valid @ModelAttribute("cart")Cart cart, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("products", productRepository.findAll());
            model.addAttribute("productId", productRepository.findAll());
            return "/furniture";
        }
        if(userAccountRepository.findByUsername(cart.getUsername())!=null && userAccountRepository.findByUsername(cart.getUsername()).getPassword().equalsIgnoreCase(cart.getPassword()))
        {
            List<Order> orders= (List<Order>) orderService.getAllOrders();
            carts=new Cart(cart.getUsername(), cart.getPassword());
            model.addAttribute("history", orders.stream().filter(order -> order.getUsername().equalsIgnoreCase(cart.getUsername())).filter(order -> order.getState().equalsIgnoreCase("paid")).collect(Collectors.toList()));
            model.addAttribute("invoice", orders.stream().filter(order -> order.getUsername().equalsIgnoreCase(cart.getUsername())).filter(order -> order.getState().equalsIgnoreCase("delivering")).collect(Collectors.toList()));
            List<Order> orders1=orders.stream().filter(order -> order.getState().equalsIgnoreCase("delivering")).filter(order -> order.getUsername().equalsIgnoreCase(cart.getUsername())).collect(Collectors.toList());
            float price=0.0f;
            List<Product> list= (List<Product>) productRepository.findAll();
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
