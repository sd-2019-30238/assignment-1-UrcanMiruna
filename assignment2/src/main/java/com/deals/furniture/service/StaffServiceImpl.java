package com.deals.furniture.service;

import com.deals.furniture.model.StaffAccount;
import com.deals.furniture.model.StaffAccountRepository;
import com.deals.furniture.model.UserAccount;
import com.deals.furniture.model.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {


    @Autowired
    private StaffAccountRepository staffAccountRepository;

    @Override
    public List<StaffAccount> getAllUSers() {
        Iterable <StaffAccount> userAccounts = staffAccountRepository.findAll();
        List<StaffAccount> users = new ArrayList<>();
        for(StaffAccount userAccount:userAccounts){
            users.add(userAccount);
        }
        return users;
    }

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
    public StaffAccount findByUsename(String user) {
        return staffAccountRepository.findByUsername(user);
    }
}
