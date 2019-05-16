package com.deals.furniture.controller;


import com.deals.furniture.model.*;
import com.deals.furniture.service.OrderService;
import com.deals.furniture.service.ProductService;
import com.deals.furniture.service.StaffService;
import com.deals.furniture.service.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userAccountRepository;
    private OrderRepository orderRepository;

    @Autowired
    private StaffService staffService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/all")
    public String showAll(Model model){
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("productId", productRepository.findAll());

        return "/furniture";
    }

    @PostMapping("/register")
    public String getData(ServletWebRequest request, @Valid @ModelAttribute("userAccount") UserAccount userAccount, BindingResult result){
       if(result.hasErrors()){
           return "/register";
       }
       if(userAccountRepository.findByUsername(userAccount.getUsername())==null && staffService.findByUsename(userAccount.getUsername())==null) {
           if (request.getParameterValues("user") != null) {

               userAccountRepository.addUser(userAccount);
               return "/hello";
           } else {
               StaffAccount staffAccount = new StaffAccount(userAccount.getName(), userAccount.getAge(), userAccount.getAddress(), userAccount.getUsername(), userAccount.getPassword());
               staffService.addUser(staffAccount);
               return "/home";
           }
       }
       return "/tt";

    }


   @PostMapping("/order")
   public String placeOrder(@Valid @ModelAttribute("cart")Cart cart, BindingResult result){
       if(result.hasErrors()){
           return "/order";
       }
       UserAccount userAccount=userAccountRepository.findByUsername(cart.getUsername());
       if(cart!=null && cart.getUsername().equals(userAccount.getUsername()) && cart.getPassword().equals(userAccount.getPassword())){
           return "/hello";
       }
       return "/logorreg";
   }

    @GetMapping("/login")
    public String login(Model model){
        return "/login";
    }

}
