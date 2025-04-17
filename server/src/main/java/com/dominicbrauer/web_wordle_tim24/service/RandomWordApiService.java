package com.dominicbrauer.web_wordle_tim24.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RandomWordApiService {

  private final RestTemplate restTemplate;

  public RandomWordApiService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public String fetchRandomWord() {
    String url = "https://random-word-api.vercel.app/api?words=1&length=5";
    return restTemplate.getForObject(url, String.class);
  }
  
}
