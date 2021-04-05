package com.example.auth.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.auth.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {

}
