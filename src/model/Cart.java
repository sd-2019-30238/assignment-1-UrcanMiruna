package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Cart {
    private UserAccount user;
    private List<Product> productList;
    private float price;

    public Cart(UserAccount user) {
        this.user = user;
        this.productList = new ArrayList<>();

    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
    public void addProduct(Product product){
        this.productList.add(product);
    }
    public void deleteAll(){
        this.productList = new ArrayList<>();
    }

    public void setPrice(){
        for(Product p : productList){
            price += p.getPrice() * p.getAmount();
        }
    }
    public float getPrice(){
        return this.price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(getUser(), cart.getUser()) &&
                Objects.equals(getProductList(), cart.getProductList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getProductList());
    }

    @Override
    public String toString() {
        return "Cart{" +
                "user=" + user +
                ", productList=" + productList +
                '}';
    }
}
