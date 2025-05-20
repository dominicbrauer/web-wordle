package dev.dominicbrauer.web_wordle_tim24.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.dominicbrauer.web_wordle_tim24.auth.service.SessionService;

@RestController
@RequestMapping("/session")
public class SessionController {

  @Autowired
  private SessionService sessionService;

  @PostMapping("/get-user")
  @CrossOrigin(
    allowedHeaders = "*",
    exposedHeaders = "*",
    methods = {RequestMethod.POST, RequestMethod.OPTIONS},
    allowCredentials = "true",
    origins = "http://localhost:4321"
  )
  public ResponseEntity<Void> requestSessionData(@RequestBody String cookieValue) {
    System.out.println(cookieValue);

    return ResponseEntity.noContent().build();
  }

}
