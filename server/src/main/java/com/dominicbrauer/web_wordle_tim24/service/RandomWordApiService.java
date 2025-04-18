package com.dominicbrauer.web_wordle_tim24.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RandomWordApiService {

  private final RestTemplate restTemplate;

  public RandomWordApiService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  /**
   * 
   * @return Random five-letter word from API
   */
  public String fetchRandomWord() {
    ResponseEntity<List<String>> response = restTemplate.exchange(
    "https://random-word-api.vercel.app/api?words=1&length=5",
    HttpMethod.GET,
    null,
    new ParameterizedTypeReference<List<String>>() {}
    );

    List<String> list = response.getBody();

    if (list != null && !list.isEmpty()) {
      return list.get(0);
    }
    return "WORD_ERR";
  }
  
}
