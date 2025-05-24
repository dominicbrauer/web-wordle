package dev.dominicbrauer.web_wordle_tim24.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.dominicbrauer.web_wordle_tim24.auth.model.UserEntity;


public interface UserH2Repository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByEmail(String email);

}
