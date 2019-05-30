package com.a3.bfd.controller;

import com.a3.bfd.model.Product;
import com.a3.bfd.readService.OrderServiceR;
import com.a3.bfd.readService.ProductServiceR;
import com.a3.bfd.readService.StaffServiceR;
import com.a3.bfd.writeService.StaffServiceW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/products")
public class ProductControllerGet {
    @Autowired
    private ProductServiceR productServiceR;
    @Autowired
    private OrderServiceR orderServiceR;
    @Autowired
    private StaffServiceR staffServiceR;
    @Autowired
    private StaffServiceW staffServiceW;
    @GetMapping(path = "/filter/kitchen")
    public String kitchen(Model model){
        staffServiceW.appyDiscout("kitchen");
        model.addAttribute("products", productServiceR.getAllProducts());
        model.addAttribute("orders", orderServiceR.getAllOrders());
        return "/staffPage";
    }
    @GetMapping(path="/filter/bedroom")
    public String bedroom(Model model){
        staffServiceW.appyDiscout("bedroom");
        model.addAttribute("products", productServiceR.getAllProducts());
        model.addAttribute("orders", orderServiceR.getAllOrders());
        return "/staffPage";
    }
    @GetMapping(path="/filter/livingroom")
    public String livingroom(Model model){
        staffServiceW.appyDiscout("livingroom");
        model.addAttribute("products", productServiceR.getAllProducts());
        model.addAttribute("orders", orderServiceR.getAllOrders());
        return "/staffPage";
    }
    @GetMapping(path="/filter/office")
    public String office(Model model){
        staffServiceW.appyDiscout("office");
        model.addAttribute("products", productServiceR.getAllProducts());
        model.addAttribute("orders", orderServiceR.getAllOrders());
        return "/staffPage";
    }


    @GetMapping(path="/type/kitchen")
    public String kitchenType(Model model){
        List<Product> products= productServiceR.getAllProducts();
        model.addAttribute("products",   products.stream().filter(product -> product.getType().equalsIgnoreCase("kitchen")).collect(Collectors.toList()));
        return "/furniture";
    }

    @GetMapping(path="/type/bedroom")
    public String bedroomType(Model model){
        List<Product> products1 = productServiceR.getAllProducts();
        model.addAttribute("products", products1.stream().filter(product -> product.getType().equalsIgnoreCase("bedroom")).collect(Collectors.toList()));
        return "/furniture";
    }
    @GetMapping(path="/type/office")
    public String officeType(Model model){
        List<Product> products= productServiceR.getAllProducts();
        model.addAttribute("products", products.stream().filter(product -> product.getType().equalsIgnoreCase("office")).collect(Collectors.toList()));
        return "/furniture";
    }
    @GetMapping(path="/type/living-room")
    public String livingType(Model model){
        List<Product> products= productServiceR.getAllProducts();
        model.addAttribute("products", products.stream().filter(product -> product.getType().equalsIgnoreCase("livingroom")).collect(Collectors.toList()));
        return "/furniture";
    }
    @GetMapping(path="/type/price")
    public String price(Model model){
        List<Product> products= productServiceR.getAllProducts();
        model.addAttribute("products", products.stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList()));
        return "/furniture";
    }
    @GetMapping(path="/type/name")
    public String name(Model model){
        List<Product> products= productServiceR.getAllProducts();
        model.addAttribute("products", products.stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList()));
        return "/furniture";
    }
    @GetMapping(path = "/addProduct")
    public String add(){
        return "/addProduct";
    }

}
