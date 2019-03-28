package model;

import dao.OrderAccess;
import dao.UserAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Invoice {
    private float totalPrice;
    private UserAccount user;
    private List<Product> productList;

    public void setTotalPrice(float price){
       this.totalPrice=price;
    }

    public Invoice( UserAccount user, List<Product> products) {
        this.user = user;
        productList=products;
    }

    public Invoice(UserAccount user) {
        this.user = user;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public UserAccount getUser() {
        return user;
    }

    public List<Product> getProductList() {
        return productList;
    }



    @Override
    public String toString() {
        return "Invoice{" +
                "totalPrice=" + totalPrice +
                ", user=" + user +
                ", productList=" + productList +
                '}';
    }
}
