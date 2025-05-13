package dev.dominicbrauer.web_wordle_tim24.service.game;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import dev.dominicbrauer.web_wordle_tim24.lib.Constants;
import dev.dominicbrauer.web_wordle_tim24.model.game.Char;
import dev.dominicbrauer.web_wordle_tim24.model.game.GameSession;
import dev.dominicbrauer.web_wordle_tim24.model.game.Guess;
import jakarta.servlet.http.HttpSession;

@Service
public class GuessFeedbackService {

  private final ValidateWordApiService validateWordApiService;
  public GuessFeedbackService(ValidateWordApiService validateWordApiService) {
    this.validateWordApiService = validateWordApiService;
  }


  public String[] handleCharCheck(char[] guess, char[] solution) {
    String[] colors = new String[Constants.WORD_LENGTH];
    boolean[] matched = new boolean[Constants.WORD_LENGTH];

    for (int i = 0; i < Constants.WORD_LENGTH; i++) {
      if (guess[i] == solution[i]) {
        colors[i] = "green";
        matched[i] = true;
        guess[i] = ' ';
      }
    }

    for (int i = 0; i < Constants.WORD_LENGTH; i++) {
      if (colors[i] != null) continue;

      for (int j = 0; j < Constants.WORD_LENGTH; j++) {
        if (!matched[j] && guess[i] == solution[j]) {
          colors[i] = "yellow";
          matched[j] = true;
          break;
        }
      }
      if (colors[i] == null) {
        colors[i] = "gray";
      }
    }
    return colors;
  }


  /**
   * Evaluates the GameSession object
   * @param gameSession Current players GameSession
   * @param session Current players HttpSession
   * @return Random five-letter word from API
   */
  public GameSession updateGameState(GameSession gameSession, HttpSession session) {
    String solutionWord = (String) session.getAttribute("solutionWord");
    String currentGuessString = gameSession.current_guess();
    char[] currentGuess = currentGuessString.toCharArray();

    if (!validateWordApiService.valid(currentGuessString)) {
      return new GameSession(
        "return_feedback",
        gameSession.guesses_used(),
        gameSession.current_guess(),
        false,
        gameSession.guesses(),
        gameSession.current_game_index(),
        gameSession.scores()
      );
    }

    ArrayList<Char> character_list = new ArrayList<>();
    String[] colors = this.handleCharCheck(currentGuess.clone(), solutionWord.toCharArray());

    for (int i = 0; i < Constants.WORD_LENGTH; i++) {
      Char character = new Char(
        currentGuess[i],
        colors[i]
      );
      character_list.add(character);
    }

    ArrayList<Guess> guesses = new ArrayList<>();
    if (gameSession.guesses() != null) {
      for (Guess previousGuess : gameSession.guesses()) {
        guesses.add(previousGuess);
      }
    }
    Guess guess = new Guess(
      currentGuessString,
      currentGuessString.equals(solutionWord),
      character_list
    );
    guesses.add(guess);

    GameSession responseGameSession = new GameSession(
      "return_feedback",
      gameSession.guesses_used() + 1,
      currentGuessString,
      true,
      guesses,
      gameSession.current_game_index(),
      gameSession.scores()
    );

    return responseGameSession;
  }
  
}
