package com.demo.cashier;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface CashierService {

  double getTax(String location, @NotNull List<Product> productList) throws Exception;

  double getSubtotal(@NotNull List<Product> productList);
}
