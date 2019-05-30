package com.a3.bfd.controller;


import com.a3.bfd.decorator.Bedroom;
import com.a3.bfd.handlers.*;
import com.a3.bfd.mediator.Mediator;
import com.a3.bfd.mediator.MediatorImpl;
import com.a3.bfd.model.Product;
import com.a3.bfd.readService.OrderServiceR;
import com.a3.bfd.readService.ProductServiceR;
import com.a3.bfd.writeService.ProductServiceW;
import com.a3.bfd.writeService.StaffServiceW;
import com.sun.org.apache.regexp.internal.RE;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductControllerPost {

    @Autowired
    private ProductServiceR productServiceR;

    @Autowired
    private OrderServiceR orderServiceR;

    @Autowired
    private  AddproductHandler addproductHandler;

    @Autowired
    private DeleteProductHandler deleteProductHandler;
    @Autowired
    private KitchenDiscountHandler kitchenDiscountHandler;
    @Autowired
    private BedroomDiscountHandler bedroomDiscountHandler;
    @Autowired
    private LivingDiscountHandler livingDiscountHandler;
    @Autowired
    private OfficeDiscountHandler officeDiscountHandler;

    @Autowired
    private StaffServiceW staffServiceW;


    @PostMapping("/addProduct")
    public String getData(ServletWebRequest request, @Valid @ModelAttribute("product") Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/addProduct";
        }
        if (product.getPrice() > 0 && product.getAmount() > 0){
            Mediator mediator = new MediatorImpl();
            Request request1=new AddproductHandler();
            product.setId(0);
            addproductHandler.setProduct(product);
            mediator.handle(request1);
            model.addAttribute("products", productServiceR.getAllProducts());
            model.addAttribute("orders", orderServiceR.getAllOrders());
            return "/staffPage";
        }
        return "/tt";

    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id")Integer id, Model model){
        Optional<Product> product=productServiceR.getProductbyId(id);
        Mediator mediator=new MediatorImpl();
        Request request=new DeleteProductHandler();
        deleteProductHandler.setProduct(product.get());
        mediator.handle(request);
        model.addAttribute("products", productServiceR.getAllProducts());
        model.addAttribute("orders", orderServiceR.getAllOrders());
        return "/staffPage";
    }

    @GetMapping(path = "/filter/kitchen")
    public String kitchen(Model model){
        Mediator mediator=new MediatorImpl();
        Request request=new KitchenDiscountHandler();
        kitchenDiscountHandler.handle(request.getType());
        mediator.handle(request);
        model.addAttribute("products", productServiceR.getAllProducts());
        model.addAttribute("orders", orderServiceR.getAllOrders());
        return "/staffPage";
    }
    @GetMapping(path="/filter/bedroom")
    public String bedroom(Model model){
        Mediator mediator=new MediatorImpl();
        Request request=new BedroomDiscountHandler();
        bedroomDiscountHandler.handle(request.getType());
        mediator.handle(request);
        model.addAttribute("products", productServiceR.getAllProducts());
        model.addAttribute("orders", orderServiceR.getAllOrders());
        return "/staffPage";
    }
    @GetMapping(path="/filter/livingroom")
    public String livingroom(Model model){
        Mediator mediator=new MediatorImpl();
        Request request=new LivingDiscountHandler();
        livingDiscountHandler.handle(request.getType());
        mediator.handle(request);
        model.addAttribute("products", productServiceR.getAllProducts());
        model.addAttribute("orders", orderServiceR.getAllOrders());
        return "/staffPage";
    }
    @GetMapping(path="/filter/office")
    public String office(Model model){
        Mediator mediator=new MediatorImpl();
        Request request=new OfficeDiscountHandler();
        officeDiscountHandler.handle(request.getType());
        mediator.handle(request);
        model.addAttribute("products", productServiceR.getAllProducts());
        model.addAttribute("orders", orderServiceR.getAllOrders());
        return "/staffPage";
    }
}
