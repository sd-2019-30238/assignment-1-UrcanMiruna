package com.a3.bfd.handlers;

import com.a3.bfd.model.Product;
import com.a3.bfd.writeService.ProductServiceW;
import com.a3.bfd.writeService.StaffServiceW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KitchenDiscountHandler implements Request {
    @Autowired
    private StaffServiceW staffServiceW;

    private String type;


    @Autowired
    public KitchenDiscountHandler() {
        this.type="kitchenDiscount";
    }


    @Override
    public void handle(String type) {
        staffServiceW.appyDiscout("kitchen");
    }

    @Override
    public String getType() {
        return type;
    }

}

