package com.deals.furniture.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.net.PortUnreachableException;
import java.util.List;

@Repository

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product findProductById(Integer id);
}
