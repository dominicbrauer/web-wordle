package dev.dominicbrauer.web_wordle_tim24.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.dominicbrauer.web_wordle_tim24.model.game.GameSession;
import dev.dominicbrauer.web_wordle_tim24.service.game.GameService;
import dev.dominicbrauer.web_wordle_tim24.service.game.GuessFeedbackService;
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


  @GetMapping("/start-game")
  public ResponseEntity<GameSession> initNewGame(HttpSession session) {
    if (!session.isNew()) {
      GameSession knownSession = (GameSession) session.getAttribute("gameSession");
      return ResponseEntity.ok(new GameSession(
        "game_found",
        knownSession.guesses_used(),
        knownSession.current_guess(),
        true,
        knownSession.guesses(),
        knownSession.current_game_index(),
        knownSession.scores()
      ));
    }

    GameSession newGameSession = gameService.createNewGameSession();

    session.setAttribute("solutionWord", gameService.getRandomWord());
    session.setAttribute("gameSession", newGameSession);
    System.out.println(session.getAttribute("solutionWord").toString());
    
    return ResponseEntity.ok(newGameSession);
  }


  @PostMapping("/guess")
  public ResponseEntity<GameSession> submitGuess(@RequestBody GameSession gameSession, HttpSession session) {    
    GameSession updatedGameSession = guessFeedbackService.updateGameState(gameSession, session);

    session.setAttribute("gameSession", updatedGameSession);
    return ResponseEntity.ok(updatedGameSession);
  }


  @PostMapping("/game-continue")
  public ResponseEntity<GameSession> nextRound(@RequestBody GameSession gameSession, HttpSession session) {
    GameSession nextGameSession = new GameSession(
      "next_game",
      0,
      null,
      false,
      null,
      gameSession.current_game_index(),
      gameSession.scores()
    );

    // Score saves etc.
    
    session.setAttribute("solutionWord", gameService.getRandomWord());
    session.setAttribute("gameSession", nextGameSession);
    System.out.println(session.getAttribute("solutionWord").toString());

    return ResponseEntity.ok(nextGameSession);
  }

}