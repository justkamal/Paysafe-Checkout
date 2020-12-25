package com.paysafe.cardpayment.repository;

import org.springframework.data.repository.CrudRepository;

import com.paysafe.cardpayment.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
	
}
