package com.example.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auth.model.Customer;
import com.example.auth.repository.CustomerServiceImpl;

@Service
public class CustomerService {

	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	public void createCustomers(List<Customer> customers) {
		customers.stream().forEach(cust -> customerServiceImpl.create(cust));
	}
	
	public void getCustomers() {
		List<Customer> listCustomer = customerServiceImpl.getCustomer();
		listCustomer.stream().forEach(cust -> System.out.println(cust.getFirstName() + " " + cust.getLastName()));
	}
	
	public Customer getCustomer(String custom) {
		return customerServiceImpl.getCustomerId(custom);
	}
}
