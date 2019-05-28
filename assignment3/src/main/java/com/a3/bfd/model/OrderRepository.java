package com.a3.bfd.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> getOrderByUsername(String username);
    Order getOrderById(Integer id);
}
