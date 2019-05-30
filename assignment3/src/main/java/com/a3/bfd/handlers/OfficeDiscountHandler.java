package com.a3.bfd.handlers;

import com.a3.bfd.writeService.StaffServiceW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfficeDiscountHandler implements Request {
    @Autowired
    private StaffServiceW staffServiceW;

    private String type;


    @Autowired
    public OfficeDiscountHandler() {
        this.type="officeDiscount";
    }


    @Override
    public void handle(String type) {
        staffServiceW.appyDiscout("office");
    }

    @Override
    public String getType() {
        return type;
    }

}