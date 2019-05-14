package com.deals.furniture.service;


import com.deals.furniture.model.Product;
import com.deals.furniture.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl  implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<Product> getProductbyId(int id) {
       return  productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        Iterable<Product> p = productRepository.findAll();
        for(Product pr:p){
            list.add(pr);
        }
        return list;
    }

    @Override
    public synchronized boolean addProduct(Product product) {
        Optional<Product> p = productRepository.findById(product.getId());
        if(p.equals(null)){
            return false;
        }else{
            productRepository.save(product);
            return true;
        }
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);

    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);

    }
}
