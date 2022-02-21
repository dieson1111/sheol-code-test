package com.demo.cashier;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Data
@Component
@ConfigurationProperties("env")
public class AppProperties {
  private HashMap<String, Double> taxRate;
  private HashMap<String, ArrayList<String>> exemptType;
  private HashMap<String, String> productType;
}
