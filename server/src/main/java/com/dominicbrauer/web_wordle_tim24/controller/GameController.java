package com.dominicbrauer.web_wordle_tim24.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dominicbrauer.web_wordle_tim24.model.GameSession;
import com.dominicbrauer.web_wordle_tim24.service.GameService;

@RestController
@RequestMapping("/api")
public class GameController {

  private final GameService gameService;

  public GameController(GameService gameService) {
    this.gameService = gameService;
  }


  @GetMapping
  public ResponseEntity<String> index() {
    return ResponseEntity.ok("API is working! 🔤");
  }


  @PostMapping("/guess")
  public String submitGuess(@RequestParam String guess) {
    System.out.println(guess);
    return "Your guess is: " + guess;
  }


  @GetMapping("/new-game")
  public ResponseEntity<GameSession> initNewGame() {
    GameSession newGame = new GameSession(
      "running",
      this.gameService.rndmWord(),
      0,
      null
    );
    return ResponseEntity.ok(newGame);
  }


  @PostMapping("/game")
  public ResponseEntity<GameSession> handleGame(@RequestBody GameSession gameRequest) {
    if (!gameRequest.status().equals("new_game")) {
      return ResponseEntity.notFound().build();
    }
    GameSession newGame = new GameSession(
      "running",
      this.gameService.rndmWord(),
      0,
      null
    );
    return ResponseEntity.ok(newGame);
  }


  
  
}