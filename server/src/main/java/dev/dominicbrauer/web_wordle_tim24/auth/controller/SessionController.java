package dev.dominicbrauer.web_wordle_tim24.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {

  @GetMapping("/check")
  @CrossOrigin(
    allowedHeaders = "*",
    exposedHeaders = "*",
    methods = {RequestMethod.GET, RequestMethod.OPTIONS},
    allowCredentials = "true",
    origins = "http://localhost:4321"
  )
  public ResponseEntity<String> requestSessionData() {
    return ResponseEntity.ok("Ok");
  }

}
