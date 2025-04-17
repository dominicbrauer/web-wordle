package com.dominicbrauer.web_wordle_tim24.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GameController {

  @GetMapping
  public ResponseEntity<String> index() {
    return ResponseEntity.ok("API is working! 🔤");
  }

  @PostMapping("/guess")
  public String submitGuess(@RequestParam String guess) {
    System.out.println(guess);
    return "Your guess is: " + guess;
  }
  
}