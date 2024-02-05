package com.base.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.base.entity.Product;
import com.base.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo repo;
	
	
	@Override
	public Product SaveProduct(Product emp) {
		Product savedProduct = repo.save(emp);
		return savedProduct;
	}


	@Override
	public Optional<Product> getProductById(int pno) {
		Optional<Product> findById = repo.findById(pno);
		return findById;
	}
	
}
