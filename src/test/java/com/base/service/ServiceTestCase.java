package com.base.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.base.entity.Product;
import com.base.repo.ProductRepo;

@ExtendWith(MockitoExtension.class)
public class ServiceTestCase {

	@Mock
	private ProductRepo repo;
	
	@InjectMocks
	private ProductServiceImpl service;
	
	@Test
	public void saveProduct_Test_Not_null() {
		
		Product product = Product.builder().productName("Pen").price(10).build();
		when(repo.save(Mockito.any(Product.class))).thenReturn(product);
		
		Product savedProduct = service.SaveProduct(product);
		Assertions.assertAll(
							()-> assertThat(savedProduct).isNotNull()
				);
	}
	
	@Test
	public void getAllProductsTest() {
		Product p1 = Product.builder().productName("Laptop").price(30_000).build();
		Product p2 = Product.builder().productName("Mobile").price(40_000).build();
		Product p3 = Product.builder().productName("Iphone").price(55_000).build();
		
		List<Product> lisOfProducts = new ArrayList<Product>();
		lisOfProducts.add(p1);
		lisOfProducts.add(p2);
		lisOfProducts.add(p3);
		
		when(repo.findAll()).thenReturn(lisOfProducts);
		
		List<Product> findAllProduct = service.findAllProduct();
		
		
		Assertions.assertAll(
							()->assertThat(findAllProduct).isNotNull(),
							()->assertEquals(findAllProduct.size(), lisOfProducts.size())
						);
	}
	
	
	@Test
	public void getProductById_Test() {
		Product p1 = Product.builder().productNo(1).productName("Laptop").price(30_000).build();
		
		when(repo.findById(1)).thenReturn(Optional.ofNullable(p1));
		
		Optional<Product> productById = service.getProductById(1);
		
		Assertions.assertAll(
					()->assertNotNull(productById),
					()->assertEquals(productById.get().getProductNo(), p1.getProductNo())
				);
	}
	
	@Test
	public void saveAllTest() {
		Product p1 = Product.builder().productName("Laptop").price(30_000).build();
		Product p2 = Product.builder().productName("Mobile").price(40_000).build();
		Product p3 = Product.builder().productName("Iphone").price(55_000).build();
		
		List<Product> lisOfProducts = new ArrayList<Product>();
		lisOfProducts.add(p1);
		lisOfProducts.add(p2);
		lisOfProducts.add(p3);
		
		when(repo.saveAll(lisOfProducts)).thenReturn(lisOfProducts);
		
		List<Product> findAllProduct = service.SaveAllProducts(lisOfProducts);
		
		Assertions.assertAll(
					()->assertNotNull(findAllProduct),
					()->assertEquals(findAllProduct.size(), lisOfProducts.size())
				);
		
	}
	
	@Test
	public void updateProduct() {
		Product p1 = Product.builder().productNo(1).productName("Laptop").price(30_000).build();
		when(repo.save(Mockito.any(Product.class))).thenReturn(p1);
		
		Product updateProduct = service.updateProduct(p1);
		
		Assertions.assertAll(
						()->assertNotNull(updateProduct)
					);
		
		
	}
	
	public void deleteByIdTest() {
		Product p1 = Product.builder().productNo(1).productName("Laptop").price(30_000).build();
		when(repo.findById(p1.getProductNo())).thenReturn(Optional.ofNullable(p1));
		
		assertAll(
					()->service.deleteProductById(p1.getProductNo())
				);
	}
	
	
	
}
