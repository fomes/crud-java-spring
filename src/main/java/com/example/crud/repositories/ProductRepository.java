package com.example.crud.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.domain.product.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

  List<Product> findAllByActiveTrue();

}
