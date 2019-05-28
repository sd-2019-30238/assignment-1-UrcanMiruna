package com.a3.bfd.readService;

import com.a3.bfd.model.StaffAccount;
import com.a3.bfd.model.StaffAccountRepository;
import com.a3.bfd.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class StaffServiceRImpl implements StaffServiceR {

    @Autowired
    private StaffAccountRepository staffAccountRepository;

    @Override
    public List<StaffAccount> getAllUSers() {
        Iterable<StaffAccount> staffAccounts=staffAccountRepository.findAll();
        List<StaffAccount> userAccounts1=new ArrayList<>();
        for(StaffAccount staffAccount:staffAccounts){
            userAccounts1.add(staffAccount);
        }
        return userAccounts1;
    }

    @Override
    public StaffAccount findByUsename(String user) {
        return staffAccountRepository.findByUsername(user);
    }
}
