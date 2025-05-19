package dev.dominicbrauer.web_wordle_tim24.game.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import dev.dominicbrauer.web_wordle_tim24.game.model.GameSession;

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
      true,
      null,
      0,
      new ArrayList<>(0)
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
  public String getRandomWord() {
    String word;
    do {
      word = randomWordApiService.fetchRandomWord();
    } while (!validateWord(word));
    return word;
  }

}
