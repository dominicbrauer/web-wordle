package com.dominicbrauer.web_wordle_tim24.model;

import java.util.UUID;

public record User(
  UUID id,
  String firstName,
  String lastName,
  String email,
  int age,
  boolean isVerified
) {}
