package com.deals.furniture.controller;

import com.deals.furniture.security.SecurityConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String root(){
        return "logorreg";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "/login";
    }
    @GetMapping("/loginStaff")
    public String loginStaff(Model model){
        return "/loginStaff";
    }
    @GetMapping("/logorreg")
    public String logorreg(){
        return "logorreg";
    }



    @GetMapping("/hello")
    public String hello(){
        return "/hello";
    }

    @GetMapping("tt")
    public String tt(){
        return "/tt";
    }

    @GetMapping("/register")
    public String reg(){
        return "/register";
    }
    @GetMapping("/kitchen")
    public String k(){
        return "/kitchen";
    }

    @GetMapping("/users")
    public String back(){
        return "/users/all";
    }
    @GetMapping("/erroe1")
    public String err(){
        return "/erroe1";
    }
    @GetMapping("/message")
    public String message(){
        return "/message";
    }
    @GetMapping("/invoice")
    public String inv(){
        return "/invoice";
    }
}
