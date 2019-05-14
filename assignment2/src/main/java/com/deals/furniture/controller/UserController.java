package com.deals.furniture.controller;


import com.deals.furniture.model.Product;
import com.deals.furniture.model.ProductRepository;
import com.deals.furniture.model.UserAccount;
import com.deals.furniture.model.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/all")
    public String showAll(Model model){
        model.addAttribute("products", productRepository.findAll());
        return "/furniture";
    }
}
