package dev.dominicbrauer.web_wordle_tim24.statistics.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.dominicbrauer.web_wordle_tim24.auth.model.SessionEntity;
import dev.dominicbrauer.web_wordle_tim24.auth.service.SessionService;
import dev.dominicbrauer.web_wordle_tim24.game.model.GameSession;
import dev.dominicbrauer.web_wordle_tim24.statistics.service.StatisticsService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/stats")
public class StatisticsController {

	@Autowired
	private final StatisticsService statisticsService;
	@Autowired
	private final SessionService sessionService;

	public StatisticsController(StatisticsService statisticsService, SessionService sessionService) {
		this.statisticsService = statisticsService;
		this.sessionService = sessionService;
	}


	@PostMapping("/save")
	@CrossOrigin(
		allowedHeaders = "*",
		exposedHeaders = "*",
		methods = {RequestMethod.POST, RequestMethod.OPTIONS},
		allowCredentials = "true",
		origins = "http://localhost:4321"
	)
	public ResponseEntity<Void> saveStatistics(@RequestBody GameSession gameSession, HttpSession session, @CookieValue(name = "session", required = true) String cookieValue) {
		Optional<SessionEntity> foundSession = sessionService.findSession(cookieValue);
		if (foundSession.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		statisticsService.updateStatistics(foundSession.get().getUserId(), gameSession);
		session.invalidate();

		return ResponseEntity.ok().build();
	}
	
}
