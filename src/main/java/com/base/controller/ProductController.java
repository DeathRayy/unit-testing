package com.base.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.base.entity.Product;
import com.base.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(description = "Main controller", name = "Main")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@Operation(description = "Save Product")
	@PostMapping(value="/save", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Product> persitProduct(@RequestBody Product prod) {
		Product saveProduct = service.SaveProduct(prod);
		return new ResponseEntity<Product>(saveProduct, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/get")
	@Operation(description = "Get Product by Id")
	public Optional<Product> getProductById(@RequestParam int no) {
		Optional<Product> productById = service.getProductById(no);
		return productById;
	}
	
	@PostMapping(value="/saveall", consumes = "application/json", produces = "application/json")
	@Operation(description = "Save all list of Products")
	public ResponseEntity<List<Product>> saveAllProducts(@RequestBody List<Product> list){
		List<Product> saveAllProducts = service.SaveAllProducts(list);
		return new ResponseEntity<List<Product>>(saveAllProducts, HttpStatus.CREATED);
	}
}