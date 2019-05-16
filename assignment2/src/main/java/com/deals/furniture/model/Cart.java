package com.deals.furniture.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {
    private Integer idProduct;
    private Integer amount;
    private String username;
    private String password;

    public Cart(Integer idProduct, Integer amount, String username, String password) {
        this.idProduct = idProduct;
        this.amount = amount;
        this.username = username;
        this.password = password;
    }

    public Cart() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(getIdProduct(), cart.getIdProduct()) &&
                Objects.equals(getAmount(), cart.getAmount()) &&
                Objects.equals(getUsername(), cart.getUsername()) &&
                Objects.equals(getPassword(), cart.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdProduct(), getAmount(), getUsername(), getPassword());
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
