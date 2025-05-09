package com.dominicbrauer.web_wordle_tim24.service;

import org.springframework.stereotype.Service;

import com.dominicbrauer.web_wordle_tim24.model.GameSession;

@Service
public class GameService {
  
  private final RandomWordApiService randomWordApiService;
  private final ValidateWordApiService validateWordApiService;

  public GameService(
    RandomWordApiService randomWordApiService,
    ValidateWordApiService validateWordApiService
  ) {
    this.randomWordApiService = randomWordApiService;
    this.validateWordApiService = validateWordApiService;
  }

  public GameSession createNewGameSession() {
    GameSession gameSession = new GameSession(
      "new_game",
      0,
      null,
      false,
      null
    );
    return gameSession;
  }

  /**
   * @return True if String is a valid English word
   */
  public boolean validateWord(String word) {
    return validateWordApiService.valid(word);
  }

  /**
   * @return Random word from ApiService
   */
  public String rndmWord() {
    String word;
    do {
      word = this.randomWordApiService.fetchRandomWord();
    } while (!this.validateWord(word));
    return word;
  }

}
