package com.altimetrix.ecommerce.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.altimetrix.ecommerce.entity.Cart;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
}

