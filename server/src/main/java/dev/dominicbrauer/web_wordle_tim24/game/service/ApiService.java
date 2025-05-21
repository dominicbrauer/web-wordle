package dev.dominicbrauer.web_wordle_tim24.game.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

  private final RestTemplate restTemplate;

  public ApiService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }


  /**
   * Checks if a string is a valid English word.
   * @param word the word to check
   * @return true if the word is valid
   */
  public boolean validWord(String word) {
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


  /**
   * Fetches a random word of a given length.
   * @param length the length of the word
   * @return a random word of length {@code length}
   */
  public String fetchRandomWord(int length) {
    ResponseEntity<List<String>> response = restTemplate.exchange(
      "https://random-word-api.vercel.app/api?words=1&length=" + String.valueOf(length),
      HttpMethod.GET,
      null,
      new ParameterizedTypeReference<List<String>>() {}
    );
    List<String> list = response.getBody();

    if (list == null || list.isEmpty()) return "WORD_ERR";

    return list.get(0);
  }
  
}