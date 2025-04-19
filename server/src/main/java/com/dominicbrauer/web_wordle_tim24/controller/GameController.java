package com.dominicbrauer.web_wordle_tim24.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dominicbrauer.web_wordle_tim24.model.GameSession;
import com.dominicbrauer.web_wordle_tim24.service.GameService;

import jakarta.servlet.http.HttpSession;

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


  @GetMapping("/start-game")
  public ResponseEntity<GameSession> initNewGame(HttpSession session) {
    if (!session.isNew()) {
      return ResponseEntity.ok((GameSession) session.getAttribute("gameSession"));
    }

    GameSession newGameSession = gameService.createNewGameSession();

    session.setAttribute("solutionWord", gameService.rndmWord());
    session.setAttribute("gameSession", newGameSession);
    return ResponseEntity.ok(newGameSession);
  }


  @GetMapping("/current-word")
  public ResponseEntity<String> showGame(HttpSession session) {
    String word = (String) session.getAttribute("solutionWord");
    
    return ResponseEntity.ok("Your current word: " + word);
  }


  @GetMapping("/endGame")
  public String endGame(HttpSession session) {
    // Sitzung invalidieren (beenden)
    session.invalidate();
    
    return "redirect:/home"; // Weiterleitung zur Startseite
  }

}