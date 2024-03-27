package com.base.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.base.entity.Product;
import com.base.exception.FailedToSaveProduct;
import com.base.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.media.MediaType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@Data
@Tag(description = "Main controller", name = "Main")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@Operation(description = "Save Product")
	@PostMapping(value="/save", consumes = "application/json", produces = "application/json")
	@ApiResponse(responseCode = "201", content = {@Content(mediaType = "application/json")})
	public ResponseEntity<Product> persitProduct(@RequestBody Product prod) {
		//log.trace("Inside Save Product "+ prod);
			Product saveProduct = service.SaveProduct(prod);
			return new ResponseEntity<Product>(saveProduct, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/get")
	@Operation(description = "Get Product by Id")
	public Optional<Product> getProductById(@RequestParam int no) {
		//log.trace("Inside getProductById "+ no);
		Optional<Product> productById = service.getProductById(no);
		return productById;
	}
	
	@GetMapping(value="/all", produces = "application/json")
	@Operation(description = "Find all products")
	public ResponseEntity<List<Product>> getAllProductList(){
		List<Product> findAllProduct = service.findAllProduct();
		return new ResponseEntity<List<Product>>(findAllProduct,HttpStatus.OK);
	}
	
	@PostMapping(value="/saveall", consumes = "application/json", produces = "application/json")
	@Operation(description = "Save all list of Products")
	public ResponseEntity<List<Product>> saveAllProducts(@RequestBody List<Product> list){
		//log.trace("Inside saveAllProducts:  "+list);
		List<Product> saveAllProducts = service.SaveAllProducts(list);
		return new ResponseEntity<List<Product>>(saveAllProducts, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
	@Operation(description = "Update product by Id")
	public ResponseEntity<Product> updateProductDetails(@RequestBody Product product) {
		Product updateProduct = service.updateProduct(product);
		return new ResponseEntity<Product>(updateProduct,HttpStatus.OK);
	}
	
	@DeleteMapping(value="/delete")
	@Operation(description = "Delete product by Id")
	public ResponseEntity<?> deleteProductById(@RequestParam int productNo) {
		service.deleteProductById(productNo);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}