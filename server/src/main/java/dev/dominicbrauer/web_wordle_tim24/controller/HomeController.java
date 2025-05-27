package dev.dominicbrauer.web_wordle_tim24.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

	@GetMapping
	@CrossOrigin(
		allowedHeaders = "*",
		exposedHeaders = "*",
		methods = {RequestMethod.GET, RequestMethod.OPTIONS},
		allowCredentials = "true",
		origins = "http://localhost:4321"
	)
	public ResponseEntity<String> index() {
		return ResponseEntity.ok("Welcome to Wordle 🔤");
	}


	@GetMapping("/api")
	@CrossOrigin(
		allowedHeaders = "*",
		exposedHeaders = "*",
		methods = {RequestMethod.GET, RequestMethod.OPTIONS},
		allowCredentials = "true",
		origins = "http://localhost:4321"
	)
	public ResponseEntity<String> api() {
		return ResponseEntity.ok("API is running smoothly!");
	}

}
