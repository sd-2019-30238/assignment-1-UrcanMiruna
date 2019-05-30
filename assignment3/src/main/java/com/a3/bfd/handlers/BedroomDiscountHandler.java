package com.a3.bfd.handlers;

import com.a3.bfd.writeService.StaffServiceW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BedroomDiscountHandler implements Request {
    @Autowired
    private StaffServiceW staffServiceW;

    private String type;


    @Autowired
    public BedroomDiscountHandler() {
        this.type="BedroomDiscount";
    }


    @Override
    public void handle(String type) {
        staffServiceW.appyDiscout("Bedroom");
    }

    @Override
    public String getType() {
        return type;
    }

}