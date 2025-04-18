package com.dominicbrauer.web_wordle_tim24.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dominicbrauer.web_wordle_tim24.model.Account;
import com.dominicbrauer.web_wordle_tim24.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;


  @GetMapping
  public List<Account> getAllUsers() {
    return userService.getAllUsers();
  }


  @PostMapping("/add")
  public ResponseEntity<String> addUser(@RequestBody Account user) {
    userService.addUser(user);

    return ResponseEntity.ok("User successfully added!");
  }
}
