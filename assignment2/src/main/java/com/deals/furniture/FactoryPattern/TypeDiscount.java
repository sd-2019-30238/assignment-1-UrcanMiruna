package com.deals.furniture.FactoryPattern;

import com.deals.furniture.model.Product;
import com.deals.furniture.model.ProductRepository;
import com.deals.furniture.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TypeDiscount implements Discount {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService ;

    @Override
    public void applyDiscount() {

        List<Product> products = productService.getAllProducts();
       for(Product p :  products){
           if ( p.getType().equalsIgnoreCase("kitchen")){
               p.setPrice((float) (p.getPrice() - 0.2*p.getPrice()));
               productService.updateProduct(p);

           }
       }
    }

}
