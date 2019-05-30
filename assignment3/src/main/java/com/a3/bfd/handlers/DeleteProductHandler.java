package com.a3.bfd.handlers;

import com.a3.bfd.model.Product;
import com.a3.bfd.writeService.ProductServiceW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DeleteProductHandler implements Request {
    @Autowired
    private ProductServiceW productServiceW;

    private String type;

    private Product product;

    @Autowired
    public DeleteProductHandler() {
        this.type="deleteProduct";
    }


    @Override
    public void handle(String type) {

        System.out.println("handler: "+this.getProduct().toString());
        productServiceW.deleteProduct(product);

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
