package com.deals.furniture.service;

import com.deals.furniture.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    void addOrder(Order userAccount);
    void updateOrder(Order userAccount);
    void deleteOrder(Order userAccount);
    List<Order> getOrdersByUsername(String username);
    Float gettotalPrice(String username);
    void validateOrder(Order order);

}