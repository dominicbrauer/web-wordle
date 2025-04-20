package com.dominicbrauer.web_wordle_tim24.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dominicbrauer.web_wordle_tim24.model.Char;
import com.dominicbrauer.web_wordle_tim24.model.GameSession;
import com.dominicbrauer.web_wordle_tim24.model.Guess;
import com.dominicbrauer.web_wordle_tim24.model.Scoring;

import jakarta.servlet.http.HttpSession;

@Service
public class GuessFeedbackService {

  private final ValidateWordApiService validateWordApiService;

  public GuessFeedbackService(ValidateWordApiService validateWordApiService) {
    this.validateWordApiService = validateWordApiService;
  }

  public boolean correctIdx(char guessChar, char solutionChar) {
    return guessChar == solutionChar;
  }

  public boolean inWord(char guessChar, String solutionWord) {
    return solutionWord.contains(String.valueOf(guessChar));
  }

  /**
   * Evaluates the GameSession object
   * @return Random five-letter word from API
   */
  public GameSession updateGameState(GameSession gameSession, HttpSession session) {
    String solutionWord = (String) session.getAttribute("solutionWord");
    String currentGuessString = gameSession.current_guess();
    char[] currentGuess = gameSession.current_guess().toCharArray();

    if (!validateWordApiService.valid(currentGuessString)) {
      return gameSession;
    }

    List<Char> character_list = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      char guessChar = currentGuess[i];
      char solutionChar = solutionWord.toCharArray()[i];
      Scoring scoring = new Scoring(
        this.inWord(guessChar, solutionWord),
        this.correctIdx(guessChar, solutionChar)
      );
      Char character = new Char(
        guessChar,
        scoring
      );
      character_list.add(character);
    }
    List<Guess> guesses = new ArrayList<>();
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
