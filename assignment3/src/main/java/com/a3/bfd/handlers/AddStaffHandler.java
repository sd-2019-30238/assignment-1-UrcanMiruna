package com.a3.bfd.handlers;

import com.a3.bfd.model.StaffAccount;
import com.a3.bfd.model.UserAccount;
import com.a3.bfd.writeService.StaffServiceW;
import com.a3.bfd.writeService.UserServiceW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddStaffHandler implements Request {
    @Autowired
    private StaffServiceW staffServiceW;

    private String type;

    private StaffAccount userAccount;

    @Autowired
    public AddStaffHandler() {
        this.type="addStaff";
    }


    @Override
    public void handle(String type) {

        System.out.println("handler: "+this.getUser().toString());
        staffServiceW.addUser(this.getUser());
    }

    @Override
    public String getType() {
        return type;
    }

    public StaffAccount getUser() {
        return userAccount;
    }

    public void setStaff(StaffAccount user) {
        this.userAccount=user;
    }
}

