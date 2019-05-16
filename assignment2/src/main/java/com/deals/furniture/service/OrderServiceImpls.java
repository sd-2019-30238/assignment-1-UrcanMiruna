package com.deals.furniture.service;


import com.deals.furniture.model.Order;
import com.deals.furniture.model.OrderRepository;
import com.deals.furniture.model.Product;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpls implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Override
    public List<Order> getAllOrders() {
        Iterable<Order> orders = orderRepository.findAll();
        List<Order> orders1=new ArrayList<>();
        for(Order order:orders){
            orders1.add(order);
        }
        return orders1;
    }

    @Override
    public void addOrder(Order order) {
        Optional<Product>  product=productService.getProductbyId(order.getIdProduct());
        if(order.getAmountOrdered()<product.get().getAmount()){
            orderRepository.save(order);
            product.get().setAmount(product.get().getAmount()-order.getAmountOrdered());
            productService.updateProduct(product.get());
        }

    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public List<Order> getOrdersByUsername(String username) {
        List<Order> orders = this.getAllOrders();
        orders.stream().filter(order -> order.getUsername().equalsIgnoreCase(username)).collect(Collectors.toList());
        return orders;
    }


    @Override
    public Float gettotalPrice(String username) {
        List<Order> orders = this.getOrdersByUsername(username);
        Float price=0.0f;
        for(Order order:orders){
            Optional<Product> product=productService.getProductbyId(order.getIdProduct());
            price+=order.getAmountOrdered()* product.get().getPrice();
        }
        return price;
    }

    @Override
    public void validateOrder(Order order) {
        order.setState("paid");
        orderRepository.save(order);
    }
}
