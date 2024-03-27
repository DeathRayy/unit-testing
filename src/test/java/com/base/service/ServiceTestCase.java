package com.base.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.base.entity.Product;
import com.base.repo.ProductRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class ServiceTestCase {

	@Mock
	private ProductRepo repo;
	
	@InjectMocks
	private ProductServiceImpl service;
	
	private Product product;
	private List<Product> ListOfProduct;
	
	@BeforeEach
	public void init() {
		product = new Product(1,"Loptop",10);
		ListOfProduct = new ArrayList<Product>();
		ListOfProduct.add(new Product(4,"Iphone",1_00_000));
		ListOfProduct.add(new Product(2,"Tab",10000));
		ListOfProduct.add(new Product(3,"Mobile",15000));
	}
	
	@Test
	public void saveProduct_Test_Not_null() {
		
		when(repo.save(Mockito.any(Product.class))).thenReturn(product);
		
		Product savedProduct = service.SaveProduct(product);
		Assertions.assertAll(
							()-> assertThat(savedProduct).isNotNull()
				);
	}
	
	@Test
	public void getAllProductsTest() {
		
		when(repo.findAll()).thenReturn(ListOfProduct);
		
		List<Product> findAllProduct = service.findAllProduct();
		
		
		Assertions.assertAll(
							()->assertThat(findAllProduct).isNotNull(),
							()->assertEquals(findAllProduct.size(), ListOfProduct.size())
						);
	}
	
	
	@Test
	public void getProductById_Test() {
		
		when(repo.findById(1)).thenReturn(Optional.ofNullable(product));
		
		Optional<Product> productById = service.getProductById(1);
		
		Assertions.assertAll(
					()->assertNotNull(productById),
					()->assertEquals(productById.get().getProductNo(), product.getProductNo())
				);
	}
	
	@Test
	public void saveAllTest() {
		
		when(repo.saveAll(ListOfProduct)).thenReturn(ListOfProduct);
		
		List<Product> findAllProduct = service.SaveAllProducts(ListOfProduct);
		
		Assertions.assertAll(
					()->assertNotNull(findAllProduct),
					()->assertEquals(findAllProduct.size(), ListOfProduct.size())
				);
	}
	
	@Test
	public void updateProduct() {
		when(repo.save(Mockito.any(Product.class))).thenReturn(product);
		
		Product updateProduct = service.updateProduct(product);
		
		Assertions.assertAll(
						()->assertNotNull(updateProduct)
					);
		
	}
	
	public void deleteByIdTest() {
		
		when(repo.findById(product.getProductNo())).thenReturn(Optional.ofNullable(product));
		
		Assertions.assertAll(
					()->service.deleteProductById(product.getProductNo())
				);
	}
}
