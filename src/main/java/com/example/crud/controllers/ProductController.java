package com.example.crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.domain.product.Product;
import com.example.crud.domain.product.ProductRepository;
import com.example.crud.domain.product.RequestProduct;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
  @Autowired
  private ProductRepository productRepository;

  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
    var allProducts = productRepository.findAll();
    return new ResponseEntity<>(allProducts, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody @Valid RequestProduct data) {
    Product product = new Product(data);
    productRepository.save(product);
    return new ResponseEntity<>(product, HttpStatus.CREATED);
  }
}
