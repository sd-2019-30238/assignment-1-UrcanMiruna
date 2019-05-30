package com.a3.bfd.handlers;

import com.a3.bfd.model.Order;
import com.a3.bfd.writeService.OrderServiceW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddOrderHandler implements Request {
    @Autowired
    private OrderServiceW orderServiceW;

    private String type;

    private Order order;

    @Autowired
    public AddOrderHandler() {
        this.type="addOrder";
    }


    @Override
    public void handle(String type) {

        System.out.println("handler: "+this.getOrder().toString());
        orderServiceW.addOrder(this.getOrder());

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
