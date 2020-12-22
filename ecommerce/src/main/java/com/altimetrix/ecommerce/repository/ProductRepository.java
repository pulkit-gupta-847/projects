package com.altimetrix.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.altimetrix.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
	
	@Query(value = "select * from product where product_category=:category order by product_price asc",nativeQuery = true)
	public List<Product> findProductsByTypeSortedByPrice(@Param("category") String category);
	
	@Query(value = "select * from product where product_category=:category order by product_Name asc",nativeQuery = true)
	public List<Product> findProductsByTypeSortedByName(@Param("category") String category);
	
	@Query(value = "select * from product where product_category=:category and product_name=:productName",nativeQuery = true)
	public List<Product> searchProductByTypeAndName(@Param("category") String category,@Param("productName") String productName);
}
