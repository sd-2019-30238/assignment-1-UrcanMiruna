package com.deals.furniture.service;

import com.deals.furniture.model.StaffAccount;
import com.deals.furniture.model.UserAccount;

import java.util.List;

public interface StaffService {
    List<StaffAccount> getAllUSers();
    void addUser(StaffAccount userAccount);
    void updateUser(StaffAccount userAccount);
    void deleteUser(StaffAccount userAccount);
    StaffAccount findByUsename(String user);
}
