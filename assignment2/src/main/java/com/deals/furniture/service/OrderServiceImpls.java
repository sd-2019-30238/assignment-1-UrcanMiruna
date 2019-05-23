package com.deals.furniture.service;


import com.deals.furniture.model.Order;
import com.deals.furniture.model.OrderRepository;
import com.deals.furniture.model.Product;
import com.deals.furniture.model.ProductRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import com.deals.furniture.model.Observer;

@Service
@Transactional
public class OrderServiceImpls extends Observer implements OrderService {

    @Autowired
    private ProductRepository productRepository;

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
        Iterable<Product> products = productRepository.findAll();
        Product product=null;
        for(Product p:products){
            if(p.getId().equals(order.getIdProduct())){
                product=p;
            }
        }
        if(order.getAmountOrdered()<product.getAmount()){
            orderRepository.save(order);
            product.setAmount(product.getAmount()-order.getAmountOrdered());
            productService.updateProduct(product);
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
        return orders.stream().filter(order -> order.getUsername().equalsIgnoreCase(username)).collect(Collectors.toList());
    }


    @Override
    public Float gettotalPrice(String username) {
        List<Order> orders = this.getOrdersByUsername(username);
        Float price=0.0f;
        for(Order order:orders){
            Product product=productRepository.findProductById(order.getIdProduct());
            price+=order.getAmountOrdered()* product.getPrice();
        }
        return price;
    }

    @Override
    public void validateOrder(Order order) {
        if(order.getState().equalsIgnoreCase("delivering")){
            order.setState("paid");
            orderRepository.save(order);
            update(order);
        }
    }

    @Override
    public Order getOrderByID(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        Order order1 = null;
        order1.setId(order.get().getId());
        order1.setIdProduct(order.get().getIdProduct());
        order1.setState(order.get().getState());
        order1.setAmountOrdered(order.get().getAmountOrdered());
        order1.setUsername(order.get().getUsername());
        order1.setPassword(order.get().getPassword());
        return order1;
    }

    @Override
    public Optional<Order> getOrderId(Integer id) {
            return orderRepository.findById(id);
    }

    @Override
    public void update(Order order) {
        Optional<Product> product=productService.getProductbyId(order.getIdProduct());
        System.out.println("The order  for username "+order.getUsername()+" for the product "+order.getIdProduct()
        );
    }
}
