package com.dominicbrauer.web_wordle_tim24.service;

import org.springframework.stereotype.Service;

import com.dominicbrauer.web_wordle_tim24.model.GameSession;

@Service
public class GameService {
  
  private final RandomWordApiService randomWordApiService;

  public GameService(RandomWordApiService randomWordApiService) {
    this.randomWordApiService = randomWordApiService;
  }

  public GameSession createNewGameSession() {
    GameSession gameSession = new GameSession(
      "new_game",
      0,
      null
    );
    return gameSession;
  }

  /**
   * 
   * @return Random word from ApiService
   */
  public String rndmWord() {
    return this.randomWordApiService.fetchRandomWord();
  }

}
