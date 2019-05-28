package com.a3.bfd.writeService;

import com.a3.bfd.model.UserAccount;
import com.a3.bfd.model.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServiceWImpl implements UserServiceW {
    @Autowired
    private UserAccountRepository userAccountRepository;


    @Override
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
}
