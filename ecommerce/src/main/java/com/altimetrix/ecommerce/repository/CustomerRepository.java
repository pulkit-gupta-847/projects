package com.altimetrix.ecommerce.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.altimetrix.ecommerce.entity.Customer;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
	public List<Customer> findAll();
}

