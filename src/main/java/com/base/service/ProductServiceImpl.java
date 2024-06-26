package com.base.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.entity.Product;
import com.base.exception.FailedToSaveProduct;
import com.base.exception.ProductNotFountExeption;
import com.base.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo repo;
	
	
	@Override
	public Product SaveProduct(Product emp) throws FailedToSaveProduct {
		Product savedProduct = repo.save(emp);
		return savedProduct;
	}


	@Override
	public Optional<Product> getProductById(int pno) {
		Product findById = repo.findById(pno).orElseThrow(()-> new ProductNotFountExeption("Requested Product is not in Stock"));
		return Optional.of(findById);
	}


	@Override
	public List<Product> SaveAllProducts(List<Product> list) {
		List<Product> saveAll = repo.saveAll(list);
		return saveAll;
	}


	@Override
	public Product updateProduct(Product product) {
		Product updatedProduct = repo.save(product);
		return updatedProduct;
	}


	@Override
	public void deleteProductById(int productNo) {
		repo.deleteById(productNo);
	}


	@Override
	public List<Product> findAllProduct() {
		List<Product> allProducts = repo.findAll();
		
		return allProducts;
	}
	
}
