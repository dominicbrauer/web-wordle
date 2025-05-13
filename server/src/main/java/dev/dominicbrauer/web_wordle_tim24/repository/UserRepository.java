package dev.dominicbrauer.web_wordle_tim24.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.dominicbrauer.web_wordle_tim24.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  
}
