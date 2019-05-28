package com.a3.bfd.readService;

import com.a3.bfd.model.UserAccount;
import com.a3.bfd.model.UserAccountRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class UserServiceRImpl implements UserServiceR {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public List<UserAccount> getAllUSers() {
        Iterable<UserAccount> userAccounts=userAccountRepository.findAll();
        List<UserAccount> userAccounts1=new ArrayList<>();
        for(UserAccount userAccount:userAccounts){
            userAccounts1.add(userAccount);
        }
        return userAccounts1;
    }

    @Override
    public UserAccount findByUsername(String user) {
            return userAccountRepository.findByUsername(user);
    }
}
