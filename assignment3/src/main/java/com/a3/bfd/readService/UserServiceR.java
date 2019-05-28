package com.a3.bfd.readService;

import com.a3.bfd.model.UserAccount;

import java.util.List;

public interface UserServiceR {
    List<UserAccount> getAllUSers();
    UserAccount findByUsername(String user);

}
