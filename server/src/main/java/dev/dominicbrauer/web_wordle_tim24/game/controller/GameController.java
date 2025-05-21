package dev.dominicbrauer.web_wordle_tim24.game.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.dominicbrauer.web_wordle_tim24.game.model.Game;
import dev.dominicbrauer.web_wordle_tim24.game.model.GameSession;
import dev.dominicbrauer.web_wordle_tim24.game.service.GameService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/game")
public class GameController {

	private final GameService gameService;

	public GameController(GameService gameService) {
		this.gameService = gameService;
	}


	@GetMapping("/start")
	@CrossOrigin(
		allowedHeaders = "*",
		exposedHeaders = "*",
		methods = {RequestMethod.GET, RequestMethod.OPTIONS},
		allowCredentials = "true",
		origins = "http://localhost:4321"
	)
	public ResponseEntity<GameSession> requestGameSession(HttpSession session) {
		if (!session.isNew()) {
			GameSession knownSession = (GameSession) session.getAttribute("gameSession");
			return ResponseEntity.ok(new GameSession(
				"session_found",
				knownSession.game_index(),
				knownSession.games()
			));
		}

		GameSession newGameSession = gameService.createNewGameSession();

		session.setAttribute("currentSolutionWord", gameService.getRandomWord());
		session.setAttribute("gameSession", newGameSession);
		
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
		Game updatedGame = gameService.processGuess(gameSession.games().getLast(), session);
		gameSession.games().removeLast();
		gameSession.games().add(updatedGame);

		GameSession updatedGameSession = new GameSession(
			"return_feedback",
			gameSession.game_index(),
			gameSession.games()
		);

		if (updatedGame.guesses().getLast().was_correct()) {
			updatedGameSession = new GameSession(
				"next_game",
				gameSession.game_index() + 1,
				gameSession.games()
			);
			updatedGameSession.games().add(gameService.createNewGame());
			session.setAttribute("currentSolutionWord", gameService.getRandomWord());
		}

		session.setAttribute("gameSession", updatedGameSession);
		return ResponseEntity.ok(updatedGameSession);
	}

}