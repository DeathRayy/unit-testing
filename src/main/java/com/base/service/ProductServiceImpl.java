package com.base.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.base.entity.Product;
import com.base.exception.FailedToSaveProduct;
import com.base.exception.ProductNotFountException;
import com.base.repo.ProductRepo;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {

	private final ProductRepo repo;

	public ProductServiceImpl(ProductRepo repo) {
		this.repo = repo;
	}

	
	
	@Override
	public Product saveProduct(Product emp) throws FailedToSaveProduct {
        return repo.save(emp);
	}


	@Override
	public Optional<Product> getProductById(int pno) {
		Product findById = repo.findById(pno).orElseThrow(()-> new ProductNotFountException("Requested Product is not in Stock"));
		return Optional.of(findById);
	}


	@Override
	public List<Product> saveAllProducts(List<Product> list) {
        return repo.saveAll(list);
	}


	@Override
	public Product updateProduct(Product product) {
        return repo.save(product);
	}


	@Override
	public void deleteProductById(int productNo) {
		repo.deleteById(productNo);
	}


	@Override
	public List<Product> findAllProduct() {

        return repo.findAll();
	}
	
}
