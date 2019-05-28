package com.a3.bfd.decorator;

import com.a3.bfd.model.Product;

import java.util.List;

public interface Discount {
    List<Product> applyDiscount(List<Product> products);
}
