package com.a3.bfd.handlers;

import com.a3.bfd.model.Order;
import com.a3.bfd.model.Product;
import com.a3.bfd.writeService.OrderServiceW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateOrderHandler implements Request {
    @Autowired
    private OrderServiceW orderServiceW;


    private String type;

    private Order order;

    @Autowired
    public ValidateOrderHandler() {
        this.type="validateOrder";
    }


    @Override
    public void handle(String type) {

        System.out.println("handler: "+this.getOrder().toString());
        orderServiceW.validateOrder(this.getOrder());

    }

    @Override
    public String getType() {
        return type;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
