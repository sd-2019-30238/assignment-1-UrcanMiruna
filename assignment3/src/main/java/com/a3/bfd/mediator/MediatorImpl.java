package com.a3.bfd.mediator;

import com.a3.bfd.handlers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class MediatorImpl implements Mediator {
    private static List<Request> requests = new ArrayList<>();

    @Autowired
    private AddproductHandler addproductHandler;
    @Autowired
    private DeleteProductHandler deleteProductHandler;

    @Autowired
    private ValidateOrderHandler validateOrderHandler;

    @Autowired
    private AddUserHandler addUserHandler;

    @Autowired
    private AddStaffHandler addStaffHandler;
    @Autowired
    private AddOrderHandler addOrderHandler;

    public MediatorImpl() {
    }

    @PostConstruct
    public void build() {
        System.out.println("Test");
        requests.add(addproductHandler);
        requests.add(deleteProductHandler);
        requests.add(validateOrderHandler);
        requests.add(addUserHandler);
        requests.add(addStaffHandler);
        requests.add(addOrderHandler);
        System.out.println(requests.size());

    }

    @Override
    public void handle(Request request) {
        System.out.println("test2");
        System.out.println(requests.size());
        for (Request request1 : requests) {
            System.out.println("da");
            if (request1.getType().equalsIgnoreCase(request.getType())) {
                System.out.println(request1.getType());
                request1.handle(request1.getType());
            }
        }
    }

    @Override
    public void addHandler(Request request) {
        requests.add(request);
    }
}
