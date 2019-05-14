package com.deals.furniture.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffAccountRepository  extends CrudRepository<StaffAccount, Integer> {
}
