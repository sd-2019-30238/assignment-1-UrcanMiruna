package com.a3.bfd.readService;

import com.a3.bfd.model.Product;
import com.a3.bfd.model.ProductRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ProductSeviceRImpl implements ProductServiceR{


    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
       return (List<Product>) productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductbyId(int id) {
        return productRepository.findById(id);
    }
}
