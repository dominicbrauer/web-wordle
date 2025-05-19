package dev.dominicbrauer.web_wordle_tim24.game.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.dominicbrauer.web_wordle_tim24.game.model.GameSession;
import dev.dominicbrauer.web_wordle_tim24.game.service.GameService;
import dev.dominicbrauer.web_wordle_tim24.game.service.GuessFeedbackService;
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
  @CrossOrigin(
    allowedHeaders = "*",
    exposedHeaders = "*",
    methods = {RequestMethod.GET, RequestMethod.OPTIONS},
    allowCredentials = "true",
    origins = "http://localhost:4321"
  )
  public ResponseEntity<String> index() {
    return ResponseEntity.ok("API is working! 🔤");
  }

  @GetMapping("/start-game")
  @CrossOrigin(
    allowedHeaders = "*",
    exposedHeaders = "*",
    methods = {RequestMethod.GET, RequestMethod.OPTIONS},
    allowCredentials = "true",
    origins = "http://localhost:4321"
  )
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
  @CrossOrigin(
    allowedHeaders = "*",
    exposedHeaders = "*",
    methods = {RequestMethod.POST, RequestMethod.OPTIONS},
    allowCredentials = "true",
    origins = "http://localhost:4321"
  )
  public ResponseEntity<GameSession> submitGuess(@RequestBody GameSession gameSession, HttpSession session) {    
    GameSession updatedGameSession = guessFeedbackService.updateGameState(gameSession, session);

    session.setAttribute("gameSession", updatedGameSession);
    return ResponseEntity.ok(updatedGameSession);
  }

  @PostMapping("/game-continue")
  @CrossOrigin(
    allowedHeaders = "*",
    exposedHeaders = "*",
    methods = {RequestMethod.POST, RequestMethod.OPTIONS},
    allowCredentials = "true",
    origins = "http://localhost:4321"
  )
  public ResponseEntity<GameSession> nextRound(@RequestBody GameSession gameSession, HttpSession session) {
    System.out.println("Scores: " + gameSession.scores());

    GameSession nextGameSession = new GameSession(
      "next_game",
      0,
      null,
      false,
      null,
      gameSession.current_game_index() + 1,
      gameSession.scores()
    );
    
    session.setAttribute("solutionWord", gameService.getRandomWord());
    session.setAttribute("gameSession", nextGameSession);
    System.out.println(session.getAttribute("solutionWord").toString());

    return ResponseEntity.ok(nextGameSession);
  }

  @PostMapping("/game-over")
  @CrossOrigin(
    allowedHeaders = "*",
    exposedHeaders = "*",
    methods = {RequestMethod.POST, RequestMethod.OPTIONS},
    allowCredentials = "true",
    origins = "http://localhost:4321"
  )
  public ResponseEntity<String> handleGameOver(@RequestBody GameSession gameSession, HttpSession session) {

    System.out.println(gameSession.scores());
    // score saves

    session.invalidate();

    return ResponseEntity.ok("Progress data has been saved!");
  }

}