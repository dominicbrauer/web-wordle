package dev.dominicbrauer.web_wordle_tim24.service.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.dominicbrauer.web_wordle_tim24.model.auth.UserEntity;
import dev.dominicbrauer.web_wordle_tim24.repository.UserH2Repository;

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
