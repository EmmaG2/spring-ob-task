package com.example.obtask.homework.controllers;

import com.example.obtask.homework.models.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {
  
  @LocalServerPort
  private int port;
  @Autowired
  private TestRestTemplate restTemplate;
  @Autowired
  private RestTemplateBuilder restTemplateBuilder;
  
  private HttpHeaders headers;
  
  @BeforeEach
  void setUp() {
    restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port + "/");
    restTemplate = new TestRestTemplate(restTemplateBuilder);
    
    headers = new HttpHeaders();
    
    headers.setContentType(MediaType.APPLICATION_JSON);
  }
  
  @Test
  void getAllLaptops() {
    ResponseEntity<Laptop[]> response =
        restTemplate.getForEntity("/api/v1/laptops", Laptop[].class);
  
    assertSame(HttpStatus.NO_CONTENT, response.getStatusCode());
  }
  
  @Test
  void getByid() {
    ResponseEntity<Laptop> response =
        restTemplate.getForEntity("/api/v1/laptops/4", Laptop.class);
    
    assertSame(HttpStatus.NOT_FOUND, response.getStatusCode());
  }
  
  @Test
  void createLaptop() {
    String jsonLaptop = """
        {
          "marca": "Razer",
          "procesador": "string",
          "ram": 0
        }
        """;
  
  
    HttpEntity<String> request = new HttpEntity<>(jsonLaptop, headers);
    ResponseEntity<Laptop> response = restTemplate.exchange("/api/v1/laptops", HttpMethod.POST, request, Laptop.class);
    
    Laptop result = response.getBody();
  
    assert result != null;
    assertSame(1L, result.getId());
    assertEquals("Razer", result.getMarca());
    assertEquals(0.0, result.getRam());
  }
}