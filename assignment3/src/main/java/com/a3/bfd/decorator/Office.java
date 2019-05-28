package com.a3.bfd.decorator;

import com.a3.bfd.model.Product;
import com.a3.bfd.readService.ProductServiceR;
import com.a3.bfd.writeService.ProductServiceW;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class Office implements Discount {

    @Override
    public List<Product> applyDiscount(List<Product> products) {
        products = products.stream().filter(product -> product.getType().equalsIgnoreCase("office")).collect(Collectors.toList());
        for(Product product:products){
            product.setPrice((float) (product.getPrice()-0.1*product.getPrice()));
        }
        System.out.println("with 0.1 percentage");
        return products;
    }
}
