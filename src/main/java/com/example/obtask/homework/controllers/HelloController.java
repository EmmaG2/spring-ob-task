package com.example.obtask.homework.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("api/v1/hello")
  String sayHello() {
    return "Hello World";
  }

}
