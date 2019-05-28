package com.a3.bfd.decorator;

import com.a3.bfd.model.Product;

import java.util.List;

public abstract class Discounter implements Discount{
    protected Discount discount;
    public Discounter(Discount discount){
        this.discount=discount;
    }
    public List<Product> applyDiscount(List<Product> productse) {
        discount.applyDiscount(productse);
        return  productse;
    }
}
