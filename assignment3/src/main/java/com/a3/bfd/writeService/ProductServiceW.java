package com.a3.bfd.writeService;

import com.a3.bfd.model.Product;

public interface ProductServiceW {
    void addProduct(Product product);
    void updateProduct( Product product);
    void deleteProduct(Product product);
}
