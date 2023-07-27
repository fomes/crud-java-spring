package com.example.crud.controllers;

import java.util.List;
import java.util.Optional;

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
import com.example.crud.domain.product.ProductRepository;
import com.example.crud.domain.product.RequestProductDTO;

import jakarta.persistence.EntityNotFoundException;
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
  public ResponseEntity<Product> createProduct(@RequestBody @Valid RequestProductDTO data) {
    Product product = new Product(data);
    productRepository.save(product);
    return new ResponseEntity<>(product, HttpStatus.CREATED);
  }

  @PutMapping
  @Transactional
  public ResponseEntity<Product> updateProduct(@RequestBody @Valid RequestProductDTO data) {
    Optional<Product> optionalProduct = productRepository.findById(data.getId());
    if (optionalProduct.isPresent()) {
      Product product = optionalProduct.get();
      product.setName(data.getName());
      product.setPrice_in_cents(data.getPrice_in_cents());
      return new ResponseEntity<>(product, HttpStatus.OK);
    } else {
      throw new EntityNotFoundException();
    }
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
    Optional<Product> optionalProduct = productRepository.findById(id);
    if (optionalProduct.isPresent()) {
      Product product = optionalProduct.get();
      product.setActive(false);
      productRepository.delete(product);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      throw new EntityNotFoundException();
    }
  }

}
