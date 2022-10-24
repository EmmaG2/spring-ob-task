package com.example.obtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ObTaskApplication {
  
  public static void main(String[] args) {
    SpringApplication.run(ObTaskApplication.class, args);
  }
  
}
