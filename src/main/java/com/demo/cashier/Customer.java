package com.demo.cashier;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
public class Customer {

  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  private Long id;
  private String location;
  @OneToMany
  private List<Product> products;

  public Customer() {}
}
