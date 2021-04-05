package com.example.auth.controller;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.model.Customer;
import com.example.auth.model.User;
import com.example.auth.service.CustomerService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ActionsController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/user")
	public User login(@RequestParam("user") String username) {
		//System.out.println("login - UserController");
		String token = getJWTToken(username);
		User user = new User();
		user.setUser(username);
		user.setToken(token);		
		return user;
		
	}

	private String getJWTToken(String username) {
		//System.out.println("getJWTToken - UserController");
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
	
	@PostMapping(path = "/createCustomers")
	public ResponseEntity insertCustomers(@RequestBody List<Customer> customers) {
		customerService.createCustomers(customers);
		customerService.getCustomers();
		return new ResponseEntity<>("Customers created sucessfull", HttpStatus.OK);
	}
	
	@RequestMapping("/getDataCustomer")
	public ResponseEntity getDataCustomer(@RequestParam("IdCustomer") String idCustomer) {
		Customer customer = customerService.getCustomer(idCustomer);
		return new ResponseEntity<>("Data Customer Id " + idCustomer + ", " + 
						     		customer.getFirstName() + " " + customer.getLastName(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@RequestMapping("/getValidCredentials")
	public ResponseEntity getValidCredentials(@RequestParam("username") String username,
											  @RequestParam("password") String password) {
		if (username.equals("Jorgensen") && password.equals("07-600-2507")) {
			return new ResponseEntity<>("User Logged In" + " " + username + " - " + password, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("User not Logged In" + " " + username + " - " + password, HttpStatus.OK);
		}
		
	}
}
