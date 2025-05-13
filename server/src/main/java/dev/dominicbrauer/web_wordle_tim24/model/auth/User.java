package dev.dominicbrauer.web_wordle_tim24.model.auth;

public record User(
  Long id,
  String userName,
  String email,
  String password,
  boolean isVerified
) {}
