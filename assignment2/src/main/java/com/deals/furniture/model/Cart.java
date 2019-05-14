package com.deals.furniture.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {

    private UserAccount userAccount;
    private List<Product> products;

    public Cart(UserAccount userAccount) {
        this.userAccount = userAccount;
        this.products = new ArrayList<>();
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
    public void addProduct(Product product){
        this.products.add(product);
    }
    public void deleteAll(){
        this.products = new ArrayList<>();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(getUserAccount(), cart.getUserAccount()) &&
                Objects.equals(getProducts(), cart.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserAccount(), getProducts());
    }

    @Override
    public String toString() {
        return "Cart{" +
                "user=" + userAccount +
                ", productList=" + products +
                '}';
    }
}
