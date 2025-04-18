package com.dominicbrauer.web_wordle_tim24.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dominicbrauer.web_wordle_tim24.model.Account;

public interface UserRepository extends JpaRepository<Account, Long> {
  
}
