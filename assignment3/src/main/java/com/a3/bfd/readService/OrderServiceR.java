package com.a3.bfd.readService;

import com.a3.bfd.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderServiceR {
    List<Order> getAllOrders();
    List<Order> getOrdersByUsername(String username);
    Float gettotalPrice(String username);
    Order getOrderByID(Integer id);
    Optional<Order> getOrderId(Integer id);

}
