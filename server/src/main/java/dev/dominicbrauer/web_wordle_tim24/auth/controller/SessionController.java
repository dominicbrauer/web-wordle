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
import dev.dominicbrauer.web_wordle_tim24.statistics.model.Statistics;
import dev.dominicbrauer.web_wordle_tim24.statistics.model.StatisticsEntity;
import dev.dominicbrauer.web_wordle_tim24.statistics.service.StatisticsService;

@RestController
@RequestMapping("/session")
public class SessionController {
	
	@Autowired
	private final AuthService authService;
	@Autowired
	private final SessionService sessionService;
	@Autowired
	private final StatisticsService statisticsService;

	public SessionController(AuthService authService, SessionService sessionService, StatisticsService statisticsService) {
		this.authService = authService;
		this.sessionService = sessionService;
		this.statisticsService = statisticsService;
	}

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

		UserEntity user = authService.getUserById(session.get().getUserId()).get();
		StatisticsEntity userStats = statisticsService.getByUserId(user.getId()).get();

		User foundUser = new User(
			user.getName(),
			user.getEmail(),
			new Statistics(
				userStats.getHighestScoreTotal(),
				userStats.getHighestGameReached(),
				userStats.getHighestScoreGame(),
				userStats.getTotalGamesPlayed()
			)
		);
	
		return ResponseEntity.ok(foundUser);
	}

	@PostMapping("/signout")
	@CrossOrigin(
		allowedHeaders = "*",
		exposedHeaders = "*",
		methods = {RequestMethod.POST, RequestMethod.OPTIONS},
		allowCredentials = "true",
		origins = "http://localhost:4321"
	)
	public ResponseEntity<Void> signoutUser(@RequestBody String cookieValue) {
		sessionService.invalidateSession(cookieValue);
		return ResponseEntity.ok().build();
	}

}
