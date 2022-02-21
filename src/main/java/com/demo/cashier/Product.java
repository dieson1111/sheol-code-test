package com.demo.cashier;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
public class Product {

  @Id
  @GeneratedValue ( strategy = GenerationType.AUTO )
  private Long id;
  private String name;
  private Double price;
  private int qty;

  public Product() {}

  public Product(String name, Double price, int qty) {
    this.name = name;
    this.price = price;
    this.qty = qty;
  }
}

