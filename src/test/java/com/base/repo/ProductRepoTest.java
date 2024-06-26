package com.base.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.base.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Data
@Slf4j
@TestMethodOrder(OrderAnnotation.class)
public class ProductRepoTest {

	@Autowired
	private ProductRepo repo;
	
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
	@Order(1)
	public void saveEmployee_Test() {
		int count = (int) repo.count();
		System.out.println("Count : "+count);
		
		Product savedProduct = repo.save(product);
		log.info("saveEmployee_Test: Saved product : "+savedProduct);
		
		Assertions.assertAll(
							()-> assertNotNull(savedProduct),
							()-> assertEquals(savedProduct.getProductName(), product.getProductName()),
							()-> assertEquals(count+1, product.getProductNo())
							);
	}
	
	@Test
	@Order(2)
	public void deleteProductById_Test_Return_Empty() {
		Product savedProduct = repo.save(product);
		log.info("deleteProductById_Test_Return_Empty: Saved product : "+savedProduct);
		
		repo.deleteById(savedProduct.getProductNo());
		
		Optional<Product> productCheck = repo.findById(savedProduct.getProductNo());
		log.info("deleteProductById_Test_Return_Empty : product After delete"+productCheck);
		Assertions.assertAll(()-> assertThat(productCheck).isEmpty());
	}
	
	
	@Test
	@Order(3)
	public void saveListOfProducts_Test_Return_size() {
		
		List<Product> savedAllProducts = repo.saveAll(ListOfProduct);
		log.info("saveListOfProducts_Test_Return_size: Saved list of product : "+savedAllProducts);
		
		Assertions.assertAll(
							()-> assertNotNull(savedAllProducts),
							()-> assertEquals(savedAllProducts.size(), ListOfProduct.size())
						);
	}
	
	@Test
	@Order(4)
	public void updateProduct_Test() {
		
		List<Product> savedAllProducts = repo.saveAll(ListOfProduct);
		log.info("updateProduct_Test: Saved list of product : "+savedAllProducts);
		
		Product updateProduct = repo.findAll().get(1);
		log.info("updateProduct_Test: Saved list of product GetByID: "+updateProduct);
		updateProduct.setProductName("Mobile_Andriod");
		updateProduct.setPrice(25000);
		
		Product updateSaved = repo.save(updateProduct);
		List<Product> PostUpdate = repo.findAll();
		log.info("updateProduct_Test: Post Update list : "+PostUpdate);
		
		Assertions.assertAll(
							()-> assertNotNull(updateSaved.getProductName()),
							()-> assertEquals(updateSaved.getProductName(), updateProduct.getProductName()),
							()-> assertThat(updateSaved.getPrice()).isNotZero()
							);
	}
}
