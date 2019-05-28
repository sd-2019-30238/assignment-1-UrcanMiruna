package com.a3.bfd.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Integer> {
    UserAccount findByUsername(String usename);
}
