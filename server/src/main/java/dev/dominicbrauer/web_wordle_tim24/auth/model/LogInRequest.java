package dev.dominicbrauer.web_wordle_tim24.auth.model;

public record LogInRequest(
  String email,
  String password
) {}
