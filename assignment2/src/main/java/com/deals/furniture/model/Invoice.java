package com.deals.furniture.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Invoice {


    private Float totalPrice;
    private String username;

    public Invoice( String username,Float totalPrice) {
        this.totalPrice = totalPrice;
        this.username = username;
    }

    public Invoice() {
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public String getUsername() {
        return username;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

