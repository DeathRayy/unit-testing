package com.base.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.base.entity.Product;
import com.base.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.media.Content;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
public class ControllorTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductService service;
	
	private ObjectMapper objectMapper;
	
	private Product product;
	private List<Product> ListOfProduct;
	
	@BeforeEach
	public void init() {
		product = new Product(1,"Loptop",10);
		objectMapper = new ObjectMapper();
		ListOfProduct = new ArrayList<Product>();
		ListOfProduct.add(new Product(4,"Iphone",1_00_000));
		ListOfProduct.add(new Product(2,"Tab",10000));
		ListOfProduct.add(new Product(3,"Mobile",15000));
	}
	
	@Test
	public void saveProductTest() {
		System.out.println("Product:"+product);
		when(service.SaveProduct(product)).thenReturn(product);
		
		try {
			mockMvc.perform(post("/save")
							.contentType(MediaType.APPLICATION_JSON)
							.content(objectMapper.writeValueAsString(product))
					).andExpect(status().isCreated());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void getProductByIdTest() {
		
		when(service.getProductById(product.getProductNo())).thenReturn(Optional.ofNullable(product));
		
		try {
			ResultActions perform = mockMvc.perform(get("/get")
								.queryParam("no", "1")
								.contentType(MediaType.APPLICATION_JSON));
			perform.andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.productName", CoreMatchers.is(product.getProductName())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void getAllProductList() {
		when(service.findAllProduct()).thenReturn(ListOfProduct);
		
		try {
			ResultActions perform = mockMvc.perform(get("/all")
									.contentType(MediaType.APPLICATION_JSON));
			System.out.println("perform"+perform);
			perform.andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(ListOfProduct.size())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void saveAllProducts() {
		when(service.SaveAllProducts(ListOfProduct)).thenReturn(ListOfProduct);
		
		try {
			ResultActions perform = mockMvc.perform(post("/saveall")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(ListOfProduct)));
			perform.andExpect(status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(ListOfProduct.size())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void updateProduct() {
		when(service.updateProduct(product)).thenReturn(product);
		
		try {
			ResultActions perform = mockMvc.perform(put("/update")
							.content(objectMapper.writeValueAsString(product))
							.contentType(MediaType.APPLICATION_JSON));
			
			perform.andExpect(status().isOk());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteById() {
		doNothing().when(service).deleteProductById(1);
		
		try {
			ResultActions perform = mockMvc.perform(delete("/delete")
							.param("productNo", "1")
							.contentType(MediaType.APPLICATION_JSON));
			perform.andExpect(status().isNoContent());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
