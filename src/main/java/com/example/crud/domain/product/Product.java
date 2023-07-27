package com.example.crud.domain.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

@Table(name = "product")
@Entity(name = "product")
@EqualsAndHashCode(of = "id")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String name;
  private Integer price_in_cents;

  public Product() {
  }

  public Product(RequestProduct requestProduct) {
    this.name = requestProduct.name();
    this.price_in_cents = requestProduct.price_in_cents();
  }

  public Product(String id, String name, Integer price_in_cents) {
    this.id = id;
    this.name = name;
    this.price_in_cents = price_in_cents;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPrice_in_cents() {
    return price_in_cents;
  }

  public void setPrice_in_cents(Integer price_in_cents) {
    this.price_in_cents = price_in_cents;
  }

}
