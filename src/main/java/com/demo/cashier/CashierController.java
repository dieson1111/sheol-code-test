package com.demo.cashier;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.DecimalFormat;

@Log4j2
@Controller
public class CashierController {

  private static final DecimalFormat df = new DecimalFormat("0.00");
  private static CashierService cashierService;

  @Autowired
  public void setCashierService(CashierServiceImpl cashierService) {
    this.cashierService = cashierService;
  }

  public static void calculate (Customer customer) throws Exception {
    System.out.println(String.format("%-" + 20 + "s%-" + 10 + "s%s", "item", "price", "qty"));
    System.out.println("");

    // process calculation
    double tax = cashierService.getTax(customer.getLocation(), customer.getProducts());
    double subtotal = cashierService.getSubtotal(customer.getProducts());
    String sSubtotal = df.format(subtotal);
    String sTax = df.format(tax);
    String sTotal = df.format(tax + subtotal);
    System.out.println(String.format("%-" + (32 - sSubtotal.length()) + "s$%s", "subtotal:", sSubtotal));
    System.out.println(String.format("%-" + (32 - sTax.length()) + "s$%s", "tax:", sTax));
    System.out.println(String.format("%-" + (32 - sTotal.length()) + "s$%s", "total:", sTotal));
  }

}
