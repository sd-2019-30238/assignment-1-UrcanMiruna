package com.a3.bfd.controller;


import com.a3.bfd.readService.OrderServiceR;
import com.a3.bfd.readService.ProductServiceR;
import com.a3.bfd.readService.StaffServiceR;
import com.a3.bfd.writeService.OrderServiceW;
import com.a3.bfd.writeService.ProductServiceW;
import com.a3.bfd.writeService.StaffServiceW;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staffPage")
public class StaffControllerGet {

    @Autowired
    private StaffServiceR staffServiceR;
    @Autowired
    private ProductServiceR productServiceR;
    @Autowired
    private OrderServiceR orderServiceR;

    @GetMapping(path = "/all")
    public String settables(Model model){
        model.addAttribute("products", productServiceR.getAllProducts());
        model.addAttribute("orders", orderServiceR.getAllOrders());
        return "/staffPage";
    }
    @GetMapping("/validate")
    public String validate(Model model){
        return "/staffPage";
    }
    @GetMapping("/error")
    public String error(){
        return "/erroe1";
    }
    @GetMapping("/addProduct")
    public String add(){
        return "/addProduct";
    }
}
