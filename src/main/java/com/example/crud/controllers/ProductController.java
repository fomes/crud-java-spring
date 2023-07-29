package com.example.crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.domain.product.Product;
import com.example.crud.dto.RequestProductDTO;
import com.example.crud.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
  @Autowired
  private ProductRepository productRepository;

  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
    var allProducts = productRepository.findAllByActiveTrue();
    return new ResponseEntity<>(allProducts, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody @Valid RequestProductDTO data) {
    Product product = new Product(data);
    productRepository.save(product);
    return new ResponseEntity<>(product, HttpStatus.CREATED);
  }

  @PutMapping
  @Transactional
  public ResponseEntity<Product> updateProduct(@RequestBody @Valid RequestProductDTO data) {
    try {
      Product product = productRepository.findById(data.getId()).get();
      product.setName(data.getName());
      product.setPrice_in_cents(data.getPrice_in_cents());
      return new ResponseEntity<>(product, HttpStatus.OK);

    } catch (Exception e) {
      throw new EntityNotFoundException();
    }
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
    try {
      Product product = productRepository.findById(id).get();
      product.setActive(false);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    } catch (Exception e) {
      throw new EntityNotFoundException();
    }

  }

}
