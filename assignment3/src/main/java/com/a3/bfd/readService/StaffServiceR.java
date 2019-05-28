package com.a3.bfd.readService;

import com.a3.bfd.model.StaffAccount;

import java.util.List;

public interface StaffServiceR {
    List<StaffAccount> getAllUSers();
    StaffAccount findByUsename(String user);

}
