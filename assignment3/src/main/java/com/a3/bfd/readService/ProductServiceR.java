package com.a3.bfd.readService;

import com.a3.bfd.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductServiceR {
    List<Product> getAllProducts();
    Optional<Product> getProductbyId(int id);

}
