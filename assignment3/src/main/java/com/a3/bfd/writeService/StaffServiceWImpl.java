package com.a3.bfd.writeService;

import com.a3.bfd.decorator.*;
import com.a3.bfd.model.Product;
import com.a3.bfd.model.StaffAccount;
import com.a3.bfd.model.StaffAccountRepository;
import com.a3.bfd.readService.ProductServiceR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class StaffServiceWImpl implements StaffServiceW {

    @Autowired
    private StaffAccountRepository staffAccountRepository;

    @Autowired
    private ProductServiceW productServiceW;

    @Autowired
    private ProductServiceR productServiceR;

    @Override
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
    public void appyDiscout(String type) {
        Discount discount=null;
        if(type.equalsIgnoreCase("bedroom")){
             discount=new Bedroom();
        }else{
            if(type.equalsIgnoreCase("kitchen")) {
                discount = new Kitchen();
            }else{
                if(type.equalsIgnoreCase("office")){
                    discount=new Office();
                }else{
                    if(type.equalsIgnoreCase("livingroom")){
                        discount=new Livingroom();
                    }
                }
            }
        }
        List<Product> products=productServiceR.getAllProducts();
        List<Product> products1=discount.applyDiscount(products);
        for(Product p:products1){
            productServiceW.updateProduct(p);
        }
        System.out.print("discount applied ");
    }
}
