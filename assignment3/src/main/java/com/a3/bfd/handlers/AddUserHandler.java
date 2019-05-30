package com.a3.bfd.handlers;

import com.a3.bfd.model.Order;
import com.a3.bfd.model.UserAccount;
import com.a3.bfd.readService.UserServiceR;
import com.a3.bfd.writeService.OrderServiceW;
import com.a3.bfd.writeService.UserServiceW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddUserHandler implements Request {
    @Autowired
    private UserServiceW userServiceW;

    private String type;

    private UserAccount userAccount;

    @Autowired
    public AddUserHandler() {
        this.type="addUser";
    }


    @Override
    public void handle(String type) {

        System.out.println("handler: "+this.getUser().toString());
        userServiceW.addUser(this.getUser());

    }

    @Override
    public String getType() {
        return type;
    }

    public UserAccount getUser() {
        return userAccount;
    }

    public void setUser(UserAccount user) {
        this.userAccount=user;
    }
}
