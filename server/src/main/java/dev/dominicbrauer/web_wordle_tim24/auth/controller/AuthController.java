package dev.dominicbrauer.web_wordle_tim24.auth.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;

import dev.dominicbrauer.web_wordle_tim24.auth.model.SessionEntity;
import dev.dominicbrauer.web_wordle_tim24.auth.model.User;
import dev.dominicbrauer.web_wordle_tim24.auth.model.UserEntity;
import dev.dominicbrauer.web_wordle_tim24.auth.utility.PasswordEncryption;
import dev.dominicbrauer.web_wordle_tim24.auth.service.SessionService;
import dev.dominicbrauer.web_wordle_tim24.auth.service.UserService;

@Controller
@RequestMapping("/users")
public class AuthController {

  @Autowired
  private UserService userService;
  @Autowired
  private SessionService sessionService;

  @PostMapping(
    path = "/add",
    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
  )
  @CrossOrigin(
    allowedHeaders = "*",
    exposedHeaders = "*",
    methods = {RequestMethod.POST, RequestMethod.OPTIONS},
    allowCredentials = "true",
    origins = "http://localhost:4321"
  )
  public ResponseEntity<Void> addUser(User user) {

    // check if both passwords are the same
    
    UserEntity userEntity = new UserEntity();
    userEntity.setUserName(user.userName());
    userEntity.setEmail(user.email());
    userEntity.setPassword(PasswordEncryption.hashPassword(user.password()));

    userService.addUser(userEntity);

    UUID token = UUID.randomUUID();

    SessionEntity sessionEntity = new SessionEntity();
    sessionEntity.setSessionToken(token);
    sessionEntity.setUserId(userEntity.getId());

    sessionService.addSession(sessionEntity);

    // save token in data base

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(URI.create("http://localhost:4321/api/login?token=" + token.toString()));

    return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
  }
}
