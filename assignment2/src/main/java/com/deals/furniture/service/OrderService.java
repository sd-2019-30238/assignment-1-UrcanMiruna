package com.deals.furniture.service;

import com.deals.furniture.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();
    void addOrder(Order userAccount);
    void updateOrder(Order userAccount);
    void deleteOrder(Order userAccount);
    List<Order> getOrdersByUsername(String username);
    Float gettotalPrice(String username);
    void validateOrder(Order order);
    Order getOrderByID(Integer id);
    Optional<Order> getOrderId(Integer id);

}
