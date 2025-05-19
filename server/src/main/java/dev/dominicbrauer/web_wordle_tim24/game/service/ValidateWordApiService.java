package dev.dominicbrauer.web_wordle_tim24.game.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ValidateWordApiService {

  private final RestTemplate restTemplate;

  public ValidateWordApiService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  /**
   * Checks if the String is a valid English word.
   * 
   * @param word The word to check
   * @return true if the word is valid
   */
  public boolean valid(String word) {
    String url = "https://api.dictionaryapi.dev/api/v2/entries/en/" + word;
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<Void> entity = new HttpEntity<>(headers);

    try {
      restTemplate.exchange(
        url,
        HttpMethod.GET,
        entity,
        String.class
      );
      return true;
    } catch (RestClientException e) {
      return false;
    }
  }
  
}