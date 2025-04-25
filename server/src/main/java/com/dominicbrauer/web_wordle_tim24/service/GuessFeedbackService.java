package com.dominicbrauer.web_wordle_tim24.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dominicbrauer.web_wordle_tim24.model.Char;
import com.dominicbrauer.web_wordle_tim24.model.GameSession;
import com.dominicbrauer.web_wordle_tim24.model.Guess;

import jakarta.servlet.http.HttpSession;

@Service
public class GuessFeedbackService {

  private final ValidateWordApiService validateWordApiService;
  public GuessFeedbackService(ValidateWordApiService validateWordApiService) {
    this.validateWordApiService = validateWordApiService;
  }


  public String[] handleCharCheck(char[] guess, char[] solution) {
    String[] colors = new String[5];
    boolean[] matched = new boolean[5];

    for (int i = 0; i < 5; i++) {
      if (guess[i] == solution[i]) {
        colors[i] = "green";
        matched[i] = true;
        guess[i] = ' ';
      }
    }

    for (int i = 0; i < 5; i++) {
      if (colors[i] != null) continue;

      for (int j = 0; j < 5; j++) {
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
      return gameSession;
    }

    List<Char> character_list = new ArrayList<>();
    String[] colors = this.handleCharCheck(currentGuess.clone(), solutionWord.toCharArray());

    for (int i = 0; i < 5; i++) {
      Char character = new Char(
        currentGuess[i],
        colors[i]
      );
      character_list.add(character);
    }

    List<Guess> guesses = new ArrayList<>();
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
      "running",
      gameSession.guesses_used() + 1,
      currentGuessString,
      true,
      guesses
    );

    return responseGameSession;
  }
  
}
