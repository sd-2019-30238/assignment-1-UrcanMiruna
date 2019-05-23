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
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    @GetMapping("/loginStaff")
    public String login(Model model){
        return "/login";
    }

    @PostMapping("/addProduct")
    public String getData(ServletWebRequest request, @Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "/addProduct";
        }
        if (product.getPrice() > 0 && product.getAmount() > 0){
            productService.save(
                    product);
        return "/logorreg";
        }
        return "/tt";

    }
    @GetMapping("/validate")
    public String validate(Model model){
        return "/staffPage";
    }

    @PostMapping("/validate/{id}")
    public String validare(@PathVariable("id") Integer id, Model model){
        Optional<Order> order=orderService.getOrderId(id);
        orderService.validateOrder(order.get());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("orders", orderService.getAllOrders());
        return "/staffPage";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id")Integer id, Model model){
        Optional<Product> product=productService.findById(id);
        List<Order> orders = orderService.getAllOrders();
        for(Order order:orders) {
            if (order.getIdProduct().equals(id) && order.getState().equalsIgnoreCase("delivering")) {
                return "/message";
            }
        }
         productService.delete(product.get());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("orders", orderService.getAllOrders());
        return "/staffPage";
    }

    @GetMapping("/error")
    public String error(){
        return "/erroe1";
    }


}
