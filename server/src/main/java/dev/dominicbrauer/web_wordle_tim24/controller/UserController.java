package dev.dominicbrauer.web_wordle_tim24.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.dominicbrauer.web_wordle_tim24.model.auth.User;
import dev.dominicbrauer.web_wordle_tim24.service.auth.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;


  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }


  @PostMapping("/add")
  public ResponseEntity<String> addUser(@RequestBody User user) {
    userService.addUser(user);

    return ResponseEntity.ok("User successfully added!");
  }
}
