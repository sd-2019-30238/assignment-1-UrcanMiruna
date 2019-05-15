package com.deals.furniture.service;

import com.deals.furniture.model.UserAccount;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService  {
    List<UserAccount> getAllUSers();
    void addUser(UserAccount userAccount);
    void updateUser(UserAccount userAccount);
    void deleteUser(UserAccount userAccount);
    UserAccount findByUsername(String user);
}
