package com.deals.furniture.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Invoice {

    private Float totalPrice;
    private UserAccount userAccount;
    private List<Product> products;

    public Invoice(UserAccount userAccount) {
        this.userAccount = userAccount;
        products = new ArrayList<>();
    }

    public Invoice(UserAccount userAccount, List<Product> products) {
        this.userAccount = userAccount;
        this.products = products;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return  Objects.equals(getUserAccount(), invoice.getUserAccount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTotalPrice(), getUserAccount(), getProducts());
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "totalPrice=" + totalPrice +
                ", userAccount=" + userAccount +
                ", products=" + products +
                '}';
    }
}

