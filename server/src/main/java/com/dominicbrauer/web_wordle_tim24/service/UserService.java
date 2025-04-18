package com.dominicbrauer.web_wordle_tim24.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dominicbrauer.web_wordle_tim24.model.Account;
import com.dominicbrauer.web_wordle_tim24.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public Account addUser(Account user) {
    return userRepository.save(user);
  }

  public List<Account> getAllUsers() {
    return userRepository.findAll();
  }
  
}
