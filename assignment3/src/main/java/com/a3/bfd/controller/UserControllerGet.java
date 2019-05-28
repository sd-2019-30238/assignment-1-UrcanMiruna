package com.a3.bfd.controller;


import com.a3.bfd.model.Product;
import com.a3.bfd.model.ProductRepository;
import com.a3.bfd.readService.OrderServiceR;
import com.a3.bfd.readService.ProductServiceR;
import org.springframework.beans.factory.annotation.Autowired;
import com.a3.bfd.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserControllerGet {

    @Autowired
    private ProductServiceR productServiceR;

    @Autowired
    private OrderServiceR orderServiceR;

    @GetMapping("/all")
    public String showAll(Model model){
        model.addAttribute("products", productServiceR.getAllProducts());
        return "/furniture";
    }
    @GetMapping("/furniture")
    public String furniture(){
        return "/furniture";
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

    Cart carts=null;
    @GetMapping(path="/invoice")
    public String invoice(Model model) {
        List<Order> orders= (List<Order>)  orderServiceR.getAllOrders();

        model.addAttribute("history", orders.stream().filter(order -> order.getUsername().equalsIgnoreCase(carts.getUsername())).filter(order -> order.getState().equalsIgnoreCase("paid")).collect(Collectors.toList()));
        model.addAttribute("invoice", orders.stream().filter(order -> order.getUsername().equalsIgnoreCase(carts.getUsername())).filter(order -> order.getState().equalsIgnoreCase("delivering")).collect(Collectors.toList()));

        return "/invoice";
    }

    @GetMapping(path = "/loginStaff")
    public String loginStaff(){
        return "/loginStaff";
    }

    @GetMapping("/register")
    public String getPage(Model model){
        model.addAttribute("userAccount", new UserAccount());
        return "register";
    }

}
