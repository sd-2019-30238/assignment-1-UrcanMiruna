package com.a3.bfd.writeService;

import com.a3.bfd.model.UserAccount;

public interface UserServiceW {
    void addUser(UserAccount userAccount);
    void updateUser(UserAccount userAccount);
    void deleteUser(UserAccount userAccount);
}
