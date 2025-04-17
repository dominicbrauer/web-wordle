package com.dominicbrauer.web_wordle_tim24.service;

import org.springframework.stereotype.Service;

@Service
public class GameService {
  
  private final RandomWordApiService randomWordApiService;

  public GameService(RandomWordApiService randomWordApiService) {
    this.randomWordApiService = randomWordApiService;
  }

}
