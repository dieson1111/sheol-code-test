package com.demo.cashier;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class CashierServiceImpl implements CashierService {

  private static final DecimalFormat df = new DecimalFormat("0.00");
  private static AppProperties app;

  @Autowired
  public void setApp(@Qualifier("appProperties") AppProperties app) {
    this.app = app;
  }

  private boolean isExempt (String location, Product product) {
    ArrayList<String> exemptTypeByLoc = app.getExemptType().get(location);
    if (!exemptTypeByLoc.isEmpty()) {
      for (String exemptType : exemptTypeByLoc) {
        String name = product.getName().replaceAll("\\s+", "");
        if ( exemptType.equalsIgnoreCase(app.getProductType().get(name)) )
          return true;
      }
    }
    return false;
  }

  @Override
  public double getTax(String location, @NotNull List<Product> productList) throws Exception{
    // get tax rate by location
    double taxRate = app.getTaxRate().get(location) / 100;
    double tax = 0;
    log.debug("Location: " + location + ", tax rate: " + taxRate*100 + "%");

    for (Product product : productList) {
      // check if product is NOT exempt
      if (!isExempt(location, product)) {
        double subtotal = product.getQty() * product.getPrice();
        tax += subtotal * taxRate;
      }
    }
    // round up to the nearest 0.05
    log.debug("tax: $" + tax);
    double roundedTax = Math.ceil(tax * 20.0) / 20.0;
    return roundedTax;
  }

  @Override
  public double getSubtotal(@NotNull List<Product> productList) {
    double subtotal = 0;
    for (Product product : productList) {
      log.debug("product: " + product.getName() + ", qty: " + product.getQty() + ", price: " + product.getPrice());
      int space = 20;
      int priceSpace = 24 - df.format(product.getPrice()).length();
      int qtySpace =  12 - String.valueOf(product.getQty()).length() + (Math.abs(priceSpace - space));
      System.out.println(String.format("%-" + (priceSpace) + "s$%-" + (qtySpace)  + "s%s", product.getName(), df.format(product.getPrice()), product.getQty()));
      subtotal += product.getQty() * product.getPrice();
    }
    // rounding up
    BigDecimal roundedSubtotal = new BigDecimal(Double.toString(subtotal));
    roundedSubtotal = roundedSubtotal.setScale(2, RoundingMode.HALF_UP);
    return roundedSubtotal.doubleValue();
  }
}
