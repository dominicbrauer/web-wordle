package dev.dominicbrauer.web_wordle_tim24.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.dominicbrauer.web_wordle_tim24.model.User;
import dev.dominicbrauer.web_wordle_tim24.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User addUser(User user) {
    return userRepository.save(user);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
  
}
