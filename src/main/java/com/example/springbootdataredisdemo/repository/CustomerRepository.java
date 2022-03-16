package com.example.springbootdataredisdemo.repository;

import com.example.springbootdataredisdemo.model.Customer;

import java.util.Map;

public interface CustomerRepository {

    void save(Customer customer);

    Customer find(Long id);

    Map<Long, Customer> findAll();

    void update(Customer customer);

    void delete(Long... id);
}
