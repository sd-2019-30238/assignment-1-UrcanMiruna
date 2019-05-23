package com.deals.furniture.service;

import com.deals.furniture.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {


    @Autowired
    private StaffAccountRepository staffAccountRepository;

    @Autowired
    private ProductService productService;

    @Override
    public List<StaffAccount> getAllUSers() {
        Iterable <StaffAccount> userAccounts = staffAccountRepository.findAll();
        List<StaffAccount> users = new ArrayList<>();
        for(StaffAccount userAccount:userAccounts){
            users.add(userAccount);
        }
        return users;
    }

    @Override
    @Transactional

    public void addUser(StaffAccount userAccount) {
        staffAccountRepository.save(userAccount);
    }

    @Override
    public void updateUser(StaffAccount userAccount) {
        staffAccountRepository.save(userAccount);

    }

    @Override
    public void deleteUser(StaffAccount userAccount) {
        staffAccountRepository.delete(userAccount);

    }

    @Override
    public StaffAccount findByUsename(String user) {
        return staffAccountRepository.findByUsername(user);
    }

    @Override
    public void appyDiscout(String type) {
        float discount=0;
        if(type.equalsIgnoreCase("bedroom")){
            discount = 0.25f;
        }else{
            if(type.equalsIgnoreCase("kitchen")){
                discount=0.2f;
            }
            else{
                if(type.equalsIgnoreCase("livingroom")){
                    discount=0.3f;
                }else{
                    if(type.equalsIgnoreCase("office")){
                        discount=0.15f;
                    }
                }
            }
        }

        List<Product> products= productService.getAllProducts();
        for(Product p :  products){
            if ( p.getType().equalsIgnoreCase(type)){
                p.setPrice((float) (p.getPrice() - discount*p.getPrice()));
                productService.updateProduct(p);

            }
        }
    }
}
