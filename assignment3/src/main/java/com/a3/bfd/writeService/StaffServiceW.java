package com.a3.bfd.writeService;

import com.a3.bfd.model.StaffAccount;

public interface StaffServiceW {
    void addUser(StaffAccount userAccount);
    void updateUser(StaffAccount userAccount);
    void deleteUser(StaffAccount userAccount);
    void appyDiscout(String type);

}
