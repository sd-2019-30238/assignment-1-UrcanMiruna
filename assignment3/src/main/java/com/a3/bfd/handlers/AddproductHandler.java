package com.a3.bfd.handlers;

import com.a3.bfd.model.Product;
import com.a3.bfd.model.ProductRepository;
import com.a3.bfd.writeService.ProductServiceW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class AddproductHandler implements Request {

    @Autowired
    private ProductServiceW productServiceW;

    private String type;

    private Product product;

    @Autowired
    public AddproductHandler() {
        this.type="addProduct";
    }


    @Override
    public void handle(String type) {

            System.out.println("handler: "+this.getProduct().toString());
            productServiceW.addProduct(this.getProduct());

    }

    @Override
    public String getType() {
        return type;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
