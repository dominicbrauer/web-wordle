package dev.dominicbrauer.web_wordle_tim24.auth.model;

public record User(
  Long id,
  String userName,
  String email,
  String password,
  String verifyPassword
) {}
