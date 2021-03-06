package Controller;

import model.Cart;
import model.Product;
import model.UserAccount;

import java.util.ArrayList;
import java.util.List;

public class CartController {
    private List<Cart> carts;

    public CartController(List<Cart> carts) {
        this.carts = carts;
    }

    public CartController() {
    }

    public void addCart(Cart c){
        carts.add(c);
    }
    public Cart getCart(String username){
        Cart cart = null;
        for(Cart cat: carts){
            if(cat.getUser().getUsername().equals(username)){
                cart = cat;
            }
        }
        return cart;
    }
    public void deleteCart(Cart c){
        c = null;
    }
    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

}
