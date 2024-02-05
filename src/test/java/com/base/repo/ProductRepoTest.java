package com.base.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.notNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.base.entity.Product;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductRepoTest {

	@Autowired
	private ProductRepo repo;
	
	@Test
	public void saveEmployee_Test() {
		
		int count = (int) repo.count();
		System.out.println("Count : "+count);
		
		
		Product product = new Product();
		product.setProductName("Laptop");
		product.setPrice(55_00_000);
		
		Product savedProduct = repo.save(product);
		System.out.println("savedProduct"+savedProduct);
		Assertions.assertAll(
							()-> assertNotNull(savedProduct),
							()-> assertEquals("Laptop", product.getProductName()),
							()-> assertEquals(count+1, product.getProductNo())
							);
		
	}
}
