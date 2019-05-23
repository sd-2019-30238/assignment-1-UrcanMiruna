package com.deals.furniture.controller;


import com.deals.furniture.FactoryPattern.Discount;
import com.deals.furniture.FactoryPattern.DiscountFactory;
import com.deals.furniture.model.Product;
import com.deals.furniture.model.ProductRepository;
import com.deals.furniture.model.StaffAccount;
import com.deals.furniture.service.OrderService;
import com.deals.furniture.service.ProductService;
import com.deals.furniture.service.ProductServiceImpl;
import com.deals.furniture.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

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
    @GetMapping(path="/filter/office")
    public String office(Model model){
        staffService.appyDiscout("office");
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("orders", orderService.getAllOrders());
        return "/staffPage";
    }


    @GetMapping(path="/type/kitchen")
    public String kitchenType(Model model){
        List<Product> products= productService.getAllProducts();
        model.addAttribute("products",   products.stream().filter(product -> product.getType().equalsIgnoreCase("kitchen")).collect(Collectors.toList()));
        return "/furniture";
    }

    @GetMapping(path="/type/bedroom")
    public String bedroomType(Model model){
        List<Product> products1 = productService.getAllProducts();
        model.addAttribute("products", products1.stream().filter(product -> product.getType().equalsIgnoreCase("bedroom")).collect(Collectors.toList()));
        return "/furniture";
    }
    @GetMapping(path="/type/office")
    public String officeType(Model model){
        List<Product> products= productService.getAllProducts();
        model.addAttribute("products", products.stream().filter(product -> product.getType().equalsIgnoreCase("office")).collect(Collectors.toList()));
        return "/furniture";
    }
    @GetMapping(path="/type/living-room")
    public String livingType(Model model){
        List<Product> products= productService.getAllProducts();
        model.addAttribute("products", products.stream().filter(product -> product.getType().equalsIgnoreCase("livingroom")).collect(Collectors.toList()));
        return "/furniture";
    }
    @GetMapping(path="/type/price")
    public String price(Model model){
        List<Product> products= productService.getAllProducts();
        model.addAttribute("products", products.stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList()));
        return "/furniture";
    }
    @GetMapping(path="/type/name")
    public String name(Model model){
        List<Product> products= productService.getAllProducts();
        model.addAttribute("products", products.stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList()));
        return "/furniture";
    }

}
