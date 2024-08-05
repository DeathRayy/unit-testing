package com.base.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.base.entity.Product;

@Service("productService")
public interface ProductService {
	public Product saveProduct(Product emp);
	public Optional<Product> getProductById(int pno);
	public List<Product> saveAllProducts(List<Product> list);
	public Product updateProduct(Product product);
	public void deleteProductById(int productNo);
	public List<Product> findAllProduct();
}
