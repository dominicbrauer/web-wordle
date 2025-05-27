package dev.dominicbrauer.web_wordle_tim24.auth.model;

public record SignUpRequest(
	String name,
	String email,
	String password,
	String verifyPassword
) {}
