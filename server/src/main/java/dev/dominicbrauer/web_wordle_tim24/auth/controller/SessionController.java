package dev.dominicbrauer.web_wordle_tim24.auth.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.dominicbrauer.web_wordle_tim24.auth.model.SessionEntity;
import dev.dominicbrauer.web_wordle_tim24.auth.model.User;
import dev.dominicbrauer.web_wordle_tim24.auth.model.UserEntity;
import dev.dominicbrauer.web_wordle_tim24.auth.service.AuthService;
import dev.dominicbrauer.web_wordle_tim24.auth.service.SessionService;

@RestController
@RequestMapping("/session")
public class SessionController {

	@Autowired
	private SessionService sessionService;
	@Autowired
	private AuthService authService;

	@PostMapping("/get-user")
	@CrossOrigin(
		allowedHeaders = "*",
		exposedHeaders = "*",
		methods = {RequestMethod.POST, RequestMethod.OPTIONS},
		allowCredentials = "true",
		origins = "http://localhost:4321"
	)
	public ResponseEntity<User> requestUserBySession(@RequestBody String cookieValue) {
		Optional<SessionEntity> session = sessionService.findSession(cookieValue);

		if (session.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Optional<UserEntity> user = authService.getUserById(session.get().getUserId());

		if (user.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		User foundUser = new User(
			user.get().getUserName(),
			user.get().getEmail(),
			null
		);
	
		return ResponseEntity.ok(foundUser);
	}

}
