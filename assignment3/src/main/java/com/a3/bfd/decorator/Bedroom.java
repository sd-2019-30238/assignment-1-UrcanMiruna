package com.a3.bfd.decorator;

import com.a3.bfd.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class Bedroom implements Discount {

    @Override
    public List<Product> applyDiscount(List<Product> products) {
        products = products.stream().filter(product -> product.getType().equalsIgnoreCase("bedroom")).collect(Collectors.toList());
        for(Product product:products){
            product.setPrice((float) (product.getPrice()-0.25*product.getPrice()));
        }
        System.out.println("with 25 percent");
        return products;
    }
}
