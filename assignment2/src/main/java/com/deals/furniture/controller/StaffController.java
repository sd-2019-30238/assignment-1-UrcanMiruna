package com.deals.furniture.controller;


import com.deals.furniture.model.*;
import com.deals.furniture.service.OrderService;
import com.deals.furniture.service.ProductService;
import com.deals.furniture.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/staffPage")
public class StaffController {

    @Autowired
    private StaffService staffAccountRepository;


    @Autowired
    private ProductRepository productService;

    @Autowired
    private OrderService orderService;

    @GetMapping(path = "/all")
    public String settables(Model model){
        model.addAttribute("products", productService.findAll());
        model.addAttribute("orders", orderService.getAllOrders());
        return "/staffPage";
    }


}
