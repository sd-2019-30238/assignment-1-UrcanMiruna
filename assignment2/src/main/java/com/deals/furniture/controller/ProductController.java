package com.deals.furniture.controller;


import com.deals.furniture.FactoryPattern.Discount;
import com.deals.furniture.FactoryPattern.DiscountFactory;
import com.deals.furniture.model.Product;
import com.deals.furniture.model.ProductRepository;
import com.deals.furniture.model.StaffAccount;
import com.deals.furniture.service.OrderService;
import com.deals.furniture.service.ProductServiceImpl;
import com.deals.furniture.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private StaffService staffService;

    @GetMapping(path = "/all")
    public @ResponseBody List<Product> getAll(){
        return productService.getAllProducts();
    }
    @GetMapping(path = "/filter/kitchen")
    public String kitchen(Model model){
        staffService.appyDiscout("kitchen");
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("orders", orderService.getAllOrders());
        return "/staffPage";
    }
    @GetMapping(path="/filter/bedroom")
    public String bedroom(Model model){
        staffService.appyDiscout("bedroom");
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("orders", orderService.getAllOrders());
        return "/staffPage";
    }
    @GetMapping(path="/filter/livingroom")
    public String livingroom(Model model){
        staffService.appyDiscout("livingroom");
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("orders", orderService.getAllOrders());
        return "/staffPage";
    }
}
