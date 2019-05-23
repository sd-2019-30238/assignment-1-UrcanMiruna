package com.deals.furniture.model;

import com.deals.furniture.service.OrderService;

public abstract class  Observer {
    public abstract void update(Order order);
}
  