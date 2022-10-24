package com.example.obtask.homework.controllers;

import com.example.obtask.homework.models.Laptop;
import com.example.obtask.homework.repository.LaptopRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
  
  public static final String API_V_1_LAPTOPS = "/api/v1/laptops";
  private final LaptopRepository repo;
  
  public LaptopController(LaptopRepository repo) {
    this.repo = repo;
  }
  
  @ApiIgnore
  @GetMapping(API_V_1_LAPTOPS)
  ResponseEntity<List<Laptop>> getAllLaptops() {
    List<Laptop> res = repo.findAll();
    
    if (res.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    
    return ResponseEntity.ok(res);
  }
  
  @GetMapping(API_V_1_LAPTOPS + "/{id}")
  ResponseEntity<Optional<Laptop>> getByid(@PathVariable Long id) {
    Optional<Laptop> res = repo.findById(id);
    
    if (res.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    
    return ResponseEntity.ok(res);
  }
  
  @PostMapping(API_V_1_LAPTOPS)
  ResponseEntity<Laptop> createLaptop(@RequestBody Laptop laptop) {
    if (laptop.getRam() == null || laptop.getMarca() == null || laptop.getProcesador() == null) {
      return ResponseEntity.badRequest().build();
    }
    
    repo.save(laptop);
    
    return ResponseEntity.ok(laptop);
  }
  
  @PutMapping(API_V_1_LAPTOPS)
  ResponseEntity<Laptop> updateLaptop(@RequestBody Laptop laptop) {
    if (laptop.getId() == null) {
      return ResponseEntity.badRequest().build();
    }
  
    if (!repo.existsById(laptop.getId())) {
      return ResponseEntity.notFound().build();
    }
  
    return ResponseEntity.ok(repo.save(laptop));
  }
  
  @DeleteMapping(API_V_1_LAPTOPS + "/{id}")
  ResponseEntity<Optional<Laptop>> deleteLaptop(@PathVariable Long id) {
    Optional<Laptop> laptop = repo.findById(id);
    
    if (laptop.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }
    
    return ResponseEntity.ok(laptop);
    
  }
  
  @ApiIgnore
  @DeleteMapping(API_V_1_LAPTOPS)
  ResponseEntity<String> deleteAll() {
    List<Laptop> res = repo.findAll();
  
    if (res.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    
    repo.deleteAll();
    
    return ResponseEntity.ok("Todo se ha borrado de forma correcta");
  }
}
