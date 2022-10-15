package com.example.obtask.homework.controllers;

import com.example.obtask.homework.models.Laptop;
import com.example.obtask.homework.repository.LaptopRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
  
  public static final String API_V_1_LAPTOPS = "/api/v1/laptops";
  private final LaptopRepository repo;
  
  public LaptopController(LaptopRepository repo) {
    this.repo = repo;
  }
  
  @GetMapping(API_V_1_LAPTOPS)
  List<Laptop> getLaptops() {
    return repo.findAll();
  }
  
  @GetMapping(API_V_1_LAPTOPS + "/{id}")
  Optional<Laptop> getByid(@PathVariable Long id) {
    return repo.findById(id);
  }
  
  @PostMapping(API_V_1_LAPTOPS)
  Laptop createLaptop(@RequestBody Laptop laptop) {
    return repo.save(laptop);
  }
  
  @DeleteMapping(API_V_1_LAPTOPS + "/{id}")
  String deleteLaptop(@PathVariable Long id) {
    try {
      repo.deleteById(id);
      return "La laptop: " + id + "Se borro correctamente";
    } catch (EmptyResultDataAccessException e) {
      return "no se encontro la laptop, error: " + e.getMessage();
    }
  }
  
  @DeleteMapping(API_V_1_LAPTOPS)
  void deleteAll() {
    repo.deleteAll();
  }
}
