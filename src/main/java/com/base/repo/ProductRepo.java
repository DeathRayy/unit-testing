package com.base.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
