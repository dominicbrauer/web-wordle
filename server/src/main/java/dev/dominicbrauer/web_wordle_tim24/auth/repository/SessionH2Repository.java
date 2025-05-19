package dev.dominicbrauer.web_wordle_tim24.auth.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.dominicbrauer.web_wordle_tim24.auth.model.SessionEntity;


public interface SessionH2Repository extends JpaRepository<SessionEntity, UUID> {
  
}
