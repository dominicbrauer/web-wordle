package dev.dominicbrauer.web_wordle_tim24.auth.controller;

import java.net.URI;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dev.dominicbrauer.web_wordle_tim24.auth.model.LogInRequest;
import dev.dominicbrauer.web_wordle_tim24.auth.model.SessionEntity;
import dev.dominicbrauer.web_wordle_tim24.auth.model.SignUpRequest;
import dev.dominicbrauer.web_wordle_tim24.auth.model.UserEntity;
import dev.dominicbrauer.web_wordle_tim24.auth.service.AuthService;
import dev.dominicbrauer.web_wordle_tim24.auth.service.SessionService;
import dev.dominicbrauer.web_wordle_tim24.statistics.service.StatisticsService;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private final AuthService authService;
	@Autowired
	private final SessionService sessionService;
	@Autowired
	private final StatisticsService statisticsService;

	public AuthController(AuthService authService, SessionService sessionService, StatisticsService statisticsService) {
		this.authService = authService;
		this.sessionService = sessionService;
		this.statisticsService = statisticsService;
	}

	@PostMapping(
		path = "/register",
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
	)
	@CrossOrigin(
		allowedHeaders = "*",
		exposedHeaders = "*",
		methods = {RequestMethod.POST, RequestMethod.OPTIONS},
		allowCredentials = "true",
		origins = "http://localhost:4321"
	)
	public ResponseEntity<Void> registerUser(SignUpRequest signupForm) {
		HttpHeaders headers = new HttpHeaders();

		if (!authService.comparePasswords(signupForm.password(), signupForm.verifyPassword())) {
			headers.setLocation(URI.create("http://localhost:4321/signup?error=passwords"));
			return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
		}

		if (!authService.validEmail(signupForm.email())) {
			headers.setLocation(URI.create("http://localhost:4321/signup?error=invalid_email"));
			return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
		}

		if (!authService.userByEmail(signupForm.email()).isEmpty()) {
			headers.setLocation(URI.create("http://localhost:4321/signup?error=email_taken"));
			return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
		}

		UserEntity savedUser = authService.addUser(signupForm);
		statisticsService.initStatistics(savedUser.getId());
		SessionEntity session = sessionService.createSession(savedUser.getId());

		headers.setLocation(URI.create("http://localhost:4321/api/login?token=" + session.getSessionToken().toString()));
		return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
	}


	@PostMapping(
		path = "/login",
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
	)
	@CrossOrigin(
		allowedHeaders = "*",
		exposedHeaders = "*",
		methods = {RequestMethod.POST, RequestMethod.OPTIONS},
		allowCredentials = "true",
		origins = "http://localhost:4321"
	)
	public ResponseEntity<Void> loginUser(LogInRequest loginForm) {
		HttpHeaders headers = new HttpHeaders();

		if (!authService.validEmail(loginForm.email())) {
			headers.setLocation(URI.create("http://localhost:4321/login?error=wrong_credentials"));
			return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
		}

		Optional<UserEntity> savedUser = authService.userByEmail(loginForm.email());

		if (savedUser.isEmpty()) {
			headers.setLocation(URI.create("http://localhost:4321/login?error=wrong_credentials"));
			return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
		}

		if (!BCrypt.checkpw(loginForm.password(), savedUser.get().getPassword())) {
			headers.setLocation(URI.create("http://localhost:4321/login?error=wrong_credentials"));
			return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
		}

		SessionEntity session = sessionService.createSession(savedUser.get().getId());

		headers.setLocation(URI.create("http://localhost:4321/api/login?token=" + session.getSessionToken().toString()));
		return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
	}
}
