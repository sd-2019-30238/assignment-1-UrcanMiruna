package com.a3.bfd.controller;

import com.a3.bfd.handlers.DeleteProductHandler;
import com.a3.bfd.handlers.Request;
import com.a3.bfd.handlers.ValidateOrderHandler;
import com.a3.bfd.mediator.Mediator;
import com.a3.bfd.mediator.MediatorImpl;
import com.a3.bfd.model.Order;
import com.a3.bfd.readService.OrderServiceR;
import com.a3.bfd.readService.ProductServiceR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/staffPage")
public class StaffControllerPost {
    @Autowired
    private OrderServiceR orderServiceR;
    @Autowired
    private ProductServiceR productServiceR;
    @Autowired
    private ValidateOrderHandler validateOrderHandler;




    @PostMapping("/validate/{id}")
    public String validare(@PathVariable("id") Integer id, Model model){
        Optional<Order> order=orderServiceR.getOrderId(id);
        Mediator mediator=new MediatorImpl();
        Request request=new ValidateOrderHandler();
        validateOrderHandler.setOrder(order.get());
        mediator.handle(request);
        model.addAttribute("products", productServiceR.getAllProducts());
        model.addAttribute("orders", orderServiceR.getAllOrders());
        return "/staffPage";
    }

}
