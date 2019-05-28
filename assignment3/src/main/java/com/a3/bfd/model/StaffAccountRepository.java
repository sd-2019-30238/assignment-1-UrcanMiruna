package com.a3.bfd.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffAccountRepository  extends CrudRepository<StaffAccount, Integer> {
    StaffAccount findByUsername(String username);
}
