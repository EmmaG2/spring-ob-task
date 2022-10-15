package com.example.obtask.homework.models;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Getter
public class Laptop {
  
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  
  private String marca;
  private Double ram;
  private String procesador;
  
  protected Laptop() {}
}
