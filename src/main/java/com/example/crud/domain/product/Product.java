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
  private Boolean active;

  public Product() {
  }

  public Product(RequestProductDTO requestProduct) {
    this.name = requestProduct.getName();
    this.price_in_cents = requestProduct.getPrice_in_cents();
    this.active = true;
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

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

}
