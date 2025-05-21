package dev.dominicbrauer.web_wordle_tim24.game.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import dev.dominicbrauer.web_wordle_tim24.game.model.Char;
import dev.dominicbrauer.web_wordle_tim24.game.model.Game;
import dev.dominicbrauer.web_wordle_tim24.game.model.GameSession;
import dev.dominicbrauer.web_wordle_tim24.game.model.Guess;
import dev.dominicbrauer.web_wordle_tim24.game.model.Scoring;
import dev.dominicbrauer.web_wordle_tim24.lib.Constants;
import jakarta.servlet.http.HttpSession;

@Service
public class GameService {
	
	private final ApiService apiService;

	public GameService(ApiService apiService) {
		this.apiService = apiService;
	}


	/**
	 * Creates a fresh GameSession object.
	 * @return a new gameSession
	 */
	public GameSession createNewGameSession() {
		GameSession gameSession = new GameSession(
			"new_session",
			0,
			new ArrayList<>()
		);
		gameSession.games().add(createNewGame());
		return gameSession;
	}


	/**
	 * Creates a fresh Game object.
	 * @return a new game
	 */
	public Game createNewGame() {
		return new Game(
			"new_game",
			0,
			null,
			false,
			new ArrayList<>(),
			0
		);
	}


	/**
	 * Gets a random Wordle-compatible word.
	 * @return random word
	 */
	public String getRandomWord() {
		String word;
		do {
			word = apiService.fetchRandomWord(Constants.WORD_LENGTH);
		} while (!apiService.validWord(word));
		return word;
	}


	/**
	 * Evaluates the current guess from the current game.
	 * @param currentGame the current Game object
	 * @param session the player's current HttpSession
	 * @return a response Game object
	 */
	public Game processGuess(Game currentGame, HttpSession session) {
		String solutionWord = (String) session.getAttribute("currentSolutionWord");
		String currentGuess = currentGame.current_guess();

		if (!apiService.validWord(currentGuess)) {
			Game responseGame = new Game(
				"return_feedback", 
				currentGame.guesses_used(),
				currentGuess,
				false,
				currentGame.guesses(),
				currentGame.final_score()
			);
			return responseGame;
		}

		ArrayList<Scoring> colors = retrieveScorings(currentGuess.toCharArray(), solutionWord.toCharArray());

		// Build characters
		ArrayList<Char> characters = new ArrayList<>();
		for (int i = 0; i < currentGuess.length(); i++) {
			Char character = new Char(
				currentGuess.charAt(i),
				colors.get(i)
			);
			characters.add(character);
		}

		// Build game
		Game responseGame = new Game(
			"return_feedback",
			currentGame.guesses_used() + 1,
			currentGuess,
			true,
			currentGame.guesses(),
			currentGame.final_score()
		);
		responseGame.guesses().add(new Guess(
			currentGuess,
			currentGuess.equals(solutionWord),
			characters
		));

		if (currentGuess.equals(solutionWord)) {

		}

		return responseGame;
	}


	/**
	 * Compares a guess with the current solution word.
	 * @param guess CharArray version of the guess word
	 * @param solution CharArray version of the solution word
	 * @return an Array of Scoring colors for each character
	 */
	public ArrayList<Scoring> retrieveScorings(char[] guess, char[] solution) {
		ArrayList<Scoring> colors = new ArrayList<>();

		for (int i = 0; i < guess.length; i++) {
			// green check
			if (guess[i] == solution[i]) {
				colors.add(i, Scoring.GREEN);
				guess[i] = ' ';
				solution[i] = ' ';
				continue;
			}

			// yellow check
			int foundIdx = new String(solution).indexOf(guess[i], 0);
			if (foundIdx != -1) {
				colors.add(i, Scoring.YELLOW);
				guess[i] = ' ';
				solution[foundIdx] = ' ';
				continue;
			}

			// gray fallback
			colors.add(i, Scoring.GRAY);
			guess[i] = ' ';
		}

		return colors;
	}

}
