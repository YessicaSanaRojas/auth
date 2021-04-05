package com.example.auth.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.auth.model.Customer;

@Repository
public interface CustomerServiceImpl {

	public void create(Customer customer);
	
	public List<Customer> getCustomer();
	
	public Customer getCustomerId(String idCustomer);
}
