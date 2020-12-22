package com.altimetrix.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrix.ecommerce.entity.Product;
import com.altimetrix.ecommerce.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	public List<Product> getAllProductsByTypeSortedByPrice(String category){
		return productRepository.findProductsByTypeSortedByPrice(category);
	}
	
	public List<Product> getAllProductsByTypeSortedByName(String category){
		return productRepository.findProductsByTypeSortedByName(category);
	}

	public List<Product> searchProductsByTypeAndName(String category,String productName){
		return productRepository.searchProductByTypeAndName(category,productName);
	}
}
