package com.demo.cashier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class CashierApplicationTests {

  @Autowired
  private CashierController cashierController;

  @Autowired
  private CashierService cashierService;

  @Test
  void testLoadController() {
    assertThat(cashierController).isNotNull();
  }

  @Test
  void testLoadService() {
    assertThat(cashierService).isNotNull();
  }

  @Test
  void testUseCase1() {
    String location = "CA";
    List<Product> productList = new ArrayList<Product>();
    productList.add(new Product("book", 17.99, 1));
    productList.add(new Product("potato chips", 3.99, 1));

    try {
      double tax = cashierService.getTax(location, productList);
      assertEquals("Tax is not expected", 1.8, tax);

      double subtotal = cashierService.getSubtotal(productList);
      assertEquals("subtotal is not expected", 21.98, subtotal);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void testUseCase2() {
    String location = "NY";
    List<Product> productList = new ArrayList<Product>();
    productList.add(new Product("book", 17.99, 1));
    productList.add(new Product("pencil", 2.99, 3));

    try {
      double tax = cashierService.getTax(location, productList);
      assertEquals("Tax is not expected", 2.40, tax);

      double subtotal = cashierService.getSubtotal(productList);
      assertEquals("subtotal is not expected", 26.96, subtotal);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void testUseCase3() {
    String location = "NY";
    List<Product> productList = new ArrayList<Product>();
    productList.add(new Product("pencil", 2.99, 2));
    productList.add(new Product("shirt", 29.99, 1));

    try {
      double tax = cashierService.getTax(location, productList);
      assertEquals("Tax is not expected", 0.55, tax);

      double subtotal = cashierService.getSubtotal(productList);
      assertEquals("subtotal is not expected", 35.97, subtotal);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
