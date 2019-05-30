package com.a3.bfd.writeService;

import com.a3.bfd.model.Product;
import com.a3.bfd.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ProductServiceWImpl implements ProductServiceW {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addProduct(Product product) {
        System.out.println("service: "+product.toString());
        productRepository.save(product);
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
