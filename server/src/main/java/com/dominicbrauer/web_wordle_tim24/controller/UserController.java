package com.dominicbrauer.web_wordle_tim24.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dominicbrauer.web_wordle_tim24.model.User;


@RestController
public class UserController {

  ArrayList<User> users = new ArrayList<>();

  public UserController() {
    this.users.add(new User(
      UUID.randomUUID(),
      "Bubi",
      "Maximus",
      "example@gmail.com",
      69,
      true
    ));
    this.users.add(new User(
      UUID.randomUUID(),
      "Lisa",
      "Schmidt",
      "lisa.schmidt@gmail.com",
      25,
      false
    ));
    this.users.add(new User(
      UUID.randomUUID(),
      "John",
      "Doe",
      "john.doe@example.com",
      34,
      true
    ));
    this.users.add(new User(
      UUID.randomUUID(),
      "Emily",
      "Clark",
      "emily.clark@yahoo.com",
      40,
      false
    ));
    this.users.add(new User(
      UUID.randomUUID(),
      "Marc",
      "Miller",
      "marc.miller@hotmail.com",
      52,
      true
    ));
  }


  @GetMapping("/users")
  public List<User> allUsers() {
    return this.getAllUsers();
  }


  @GetMapping("/users/{id}")
  public User getUserById(@PathVariable UUID id) {
    return this.getAllUsers().stream()
      .filter(user -> user.id().equals(id))
      .findFirst().orElse(null);
  }


  @PostMapping("/users")
  public ResponseEntity<String> createUser(@RequestBody User newUser) {
    User user = new User(
      UUID.randomUUID(),
      newUser.firstName(),
      newUser.lastName(),
      newUser.email(),
      newUser.age(),
      newUser.isVerified()
    );
    this.users.add(user);
    return ResponseEntity.ok("Successful.");
  }


  public List<User> getAllUsers() {
    return this.users;
  }


  @PatchMapping("/users")
  public ResponseEntity<String> editUser(@RequestBody User newUser) {
    User selectedUser = this.getUserById(newUser.id());
    if (selectedUser == null) {
      return ResponseEntity.notFound().build();
    }

    int existingUserIndex = this.users.indexOf(selectedUser);

    User user = new User(
      selectedUser.id(),
      Objects.requireNonNullElse(newUser.firstName(), selectedUser.firstName()),
      Objects.requireNonNullElse(newUser.lastName(), selectedUser.lastName()),
      Objects.requireNonNullElse(newUser.email(), selectedUser.email()),
      Objects.requireNonNullElse(newUser.age(), selectedUser.age()),
      Objects.requireNonNullElse(newUser.isVerified(), selectedUser.isVerified())
    );

    this.users.set(existingUserIndex, user);
    
    return ResponseEntity.ok("Successful.");
  }

}