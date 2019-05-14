package com.deals.furniture.controller;


import com.deals.furniture.FactoryPattern.Discount;
import com.deals.furniture.FactoryPattern.DiscountFactory;
import com.deals.furniture.model.Product;
import com.deals.furniture.model.ProductRepository;
import com.deals.furniture.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @GetMapping(path = "/all")
    public @ResponseBody List<Product> getAll(){
        return productService.getAllProducts();
    }
    @GetMapping(path = "/filter/kitchen")
    public @ResponseBody List<Product> filter(){
        List<Product> products = productService.getAllProducts();
        for(Product p :  products){
            if ( p.getType().equalsIgnoreCase("kitchen")){
                p.setPrice((float) (p.getPrice() - 0.2*p.getPrice()));
                productService.updateProduct(p);

            }
        }
        return productService.getAllProducts();
    }
}
