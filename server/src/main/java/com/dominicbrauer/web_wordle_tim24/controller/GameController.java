package com.dominicbrauer.web_wordle_tim24.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


  @GetMapping("/new-game")
  public ResponseEntity<String> initNewGame(@RequestParam String username, HttpSession session) {
    // Benutzername in der Session speichern
    session.setAttribute("username", username);
        
    // Optional: Weitere Sitzungsdaten, z.B. der Fortschritt des Spiels
    session.setAttribute("gameSessionId", "game-12345");

    return ResponseEntity.ok("Success.");
  }


  @GetMapping("/showGame")
  public List<String> showGame(HttpSession session) {
    // Daten aus der Session abrufen
    String username = (String) session.getAttribute("username");
    String gameSessionId = (String) session.getAttribute("gameSessionId");
    
    // Verwende die Daten, um den Spieler fortzusetzen
    // z.B. Spielstatus anzeigen oder fortsetzen
    
    return List.of(username, gameSessionId, session.getId());
  }


  @GetMapping("/endGame")
  public String endGame(HttpSession session) {
    // Sitzung invalidieren (beenden)
    session.invalidate();
    
    return "redirect:/home"; // Weiterleitung zur Startseite
  }

}