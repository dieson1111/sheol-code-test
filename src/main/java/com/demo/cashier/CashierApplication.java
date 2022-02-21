package com.demo.cashier;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

@Log4j2
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class CashierApplication {

  public static void main(String[] args) {
    SpringApplication.run(CashierApplication.class, args);
    ObjectMapper mapper = new ObjectMapper();
    URL fileResource = CashierApplication.class.getResource("/customer.json");
    File file = new File(fileResource.getPath());
    try (InputStream inputStream = new FileInputStream(file);) {
        // get data from json file
        Customer customer = mapper.readValue(inputStream, Customer.class);
      CashierController.calculate(customer);
    } catch (Exception e) {
      log.error("[Error Caught] Invalid input!");
      e.printStackTrace();
    }
  }
}
