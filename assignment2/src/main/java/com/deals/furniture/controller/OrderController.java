package com.deals.furniture.controller;


import com.deals.furniture.model.Order;
import com.deals.furniture.model.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Order> getAll(){
        return orderRepository.findAll();
    }
}
