package com.deals.furniture.controller;


import com.deals.furniture.model.Product;
import com.deals.furniture.model.ProductRepository;
import com.deals.furniture.model.UserAccount;
import com.deals.furniture.model.UserAccountRepository;
import com.deals.furniture.service.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userAccountRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/all")
    public String showAll(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "/furniture";
    }
    @PostMapping("/register")
    public String getData(@Valid @ModelAttribute("userAccount") UserAccount userAccount, BindingResult result){
       if(result.hasErrors()){
           return "/register";
       }
       userAccountRepository.addUser(userAccount);
        return "/hello";
    }

}
