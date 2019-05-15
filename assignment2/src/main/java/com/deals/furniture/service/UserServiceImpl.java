package com.deals.furniture.service;

import com.deals.furniture.model.UserAccount;
import com.deals.furniture.model.UserAccountRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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
    @Transactional
    public void addUser(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }

    @Override
    public void updateUser(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }

    @Override
    public void deleteUser(UserAccount userAccount) {
        userAccountRepository.delete(userAccount);
    }

    @Override
    public UserAccount findByUsername(String user) {
       return userAccountRepository.findByUsername(user);
    }



}
