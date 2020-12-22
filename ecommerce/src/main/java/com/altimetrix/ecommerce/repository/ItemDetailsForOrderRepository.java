package com.altimetrix.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.altimetrix.ecommerce.entity.ItemDetailsForOrder;

public interface ItemDetailsForOrderRepository extends JpaRepository<ItemDetailsForOrder,Long> {

}
