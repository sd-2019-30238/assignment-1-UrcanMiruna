package com.a3.bfd.writeService;

import com.a3.bfd.model.Order;

public interface OrderServiceW {
    void addOrder(Order order);
    void updateOrder(Order order);
    void deleteOrder(Order order);
    void validateOrder(Order order);
}
