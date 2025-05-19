package dev.dominicbrauer.web_wordle_tim24.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.dominicbrauer.web_wordle_tim24.auth.model.UserEntity;
import dev.dominicbrauer.web_wordle_tim24.auth.repository.UserH2Repository;

@Service
public class UserService {

  @Autowired
  private UserH2Repository userRepository;

  public UserEntity addUser(UserEntity user) {
    return userRepository.save(user);
  }

  public List<UserEntity> getAllUsers() {
    return userRepository.findAll();
  }
  
}