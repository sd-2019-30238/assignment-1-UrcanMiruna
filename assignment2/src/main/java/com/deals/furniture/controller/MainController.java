package com.deals.furniture.controller;

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

    @GetMapping("/logorreg")
    public String logorreg(){
        return "logorreg";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "/login";
    }

    @GetMapping("/hello")
    public String hello(){
        return "/hello";
    }

    @GetMapping("tt")
    public String tt(){
        return "/tt";
    }





}
