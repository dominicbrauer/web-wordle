package com.dominicbrauer.web_wordle_tim24.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {

  @Id // Primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
  private Long id;

  private String name;
  private String email;
  private String password;


  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
}
