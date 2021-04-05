package com.example.auth.Implem;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auth.model.Customer;
import com.example.auth.repository.CustomerRepository;
import com.example.auth.repository.CustomerServiceImpl;

@Service
public class CustomerImpl implements CustomerServiceImpl {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public void create(Customer customer) {
		customerRepository.save(customer);
	}

	@Override
	public List<Customer> getCustomer() {
		List<Customer> listCustomer = (List<Customer>) customerRepository.findAll();
		return listCustomer;
	}

	@Override
	public Customer getCustomerId(String idCustomer) {
		Customer customer = new Customer();
		Optional<Customer> customerOpt = customerRepository.findById(idCustomer);
		if (customerOpt.isPresent()) {
			customer = customerOpt.get();
		}
		return customer;
	}

	
}
