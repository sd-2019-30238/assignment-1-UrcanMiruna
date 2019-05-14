package com.deals.furniture.service;

import com.deals.furniture.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductbyId(int id);
    boolean addProduct(Product product);
    void updateProduct( Product product);
    void deleteProduct(Product product);
}
