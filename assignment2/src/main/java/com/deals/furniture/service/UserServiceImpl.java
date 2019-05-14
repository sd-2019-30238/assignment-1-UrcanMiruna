package com.deals.furniture.service;

import com.deals.furniture.model.UserAccount;
import com.deals.furniture.model.UserAccountRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService{

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public List<UserAccount> getAllUSers() {
        Iterable <UserAccount> userAccounts = userAccountRepository.findAll();
        List<UserAccount> users = new ArrayList<>();
        for(UserAccount userAccount:userAccounts){
            users.add(userAccount);
        }
        return users;
    }

    @Override
    public boolean addUser(UserAccount userAccount) {
        return false;
    }

    @Override
    public void updateUser(UserAccount userAccount) {

    }

    @Override
    public void deleteUser(UserAccount userAccount) {

    }

    @Override
    public UserAccount findByUsename(String user) {
       return userAccountRepository.findByUsername(user);
    }



}
