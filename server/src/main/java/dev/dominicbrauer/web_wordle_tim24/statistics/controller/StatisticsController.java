package dev.dominicbrauer.web_wordle_tim24.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.dominicbrauer.web_wordle_tim24.game.model.GameSession;
import dev.dominicbrauer.web_wordle_tim24.statistics.service.StatisticsService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/stats")
public class StatisticsController {

	@Autowired
	private final StatisticsService statisticsService;

	public StatisticsController(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}

	@PostMapping("/save")
	@CrossOrigin(
		allowedHeaders = "*",
		exposedHeaders = "*",
		methods = {RequestMethod.POST, RequestMethod.OPTIONS},
		allowCredentials = "true",
		origins = "http://localhost:4321"
	)
	public ResponseEntity<Void> saveStatistics(@RequestBody GameSession gameSession, HttpSession session) {
		
		return ResponseEntity.ok().build();
	}
	
}
