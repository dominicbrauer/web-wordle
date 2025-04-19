package com.dominicbrauer.web_wordle_tim24.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dominicbrauer.web_wordle_tim24.model.GameSession;
import com.dominicbrauer.web_wordle_tim24.service.GameService;
import com.dominicbrauer.web_wordle_tim24.service.GuessFeedbackService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class GameController {

  private final GameService gameService;
  private final GuessFeedbackService guessFeedbackService;

  public GameController(GameService gameService, GuessFeedbackService guessFeedbackService) {
    this.gameService = gameService;
    this.guessFeedbackService = guessFeedbackService;
  }


  @GetMapping
  public ResponseEntity<String> index() {
    return ResponseEntity.ok("API is working! 🔤");
  }


  @PostMapping("/guess")
  public ResponseEntity<GameSession> submitGuess(@RequestBody GameSession gameSession, HttpSession session) {
    GameSession updatedGameSession = guessFeedbackService.updateGameState(gameSession, session);
    
    session.setAttribute("gameSession", updatedGameSession);
    return ResponseEntity.ok(updatedGameSession);
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

}