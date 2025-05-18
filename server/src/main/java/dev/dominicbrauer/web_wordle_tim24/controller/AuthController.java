package dev.dominicbrauer.web_wordle_tim24.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.dominicbrauer.web_wordle_tim24.model.auth.User;
import dev.dominicbrauer.web_wordle_tim24.model.auth.UserEntity;
import dev.dominicbrauer.web_wordle_tim24.service.auth.UserService;

@RestController
@RequestMapping("/users")
public class AuthController {

  @Autowired
  private UserService userService;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @GetMapping
  public List<UserEntity> getAllUsers() {
    return userService.getAllUsers();
  }

  @PostMapping("/add")
  public ResponseEntity<String> addUser(@RequestBody User user) {
    System.out.println(user);
    UserEntity userEntity = new UserEntity();
    userEntity.setUserName(user.userName());
    userEntity.setEmail(user.email());
    userEntity.setPassword(passwordEncoder.encode(user.password()));

    userService.addUser(userEntity);

    return ResponseEntity.ok("User successfully added!");
  }
}
