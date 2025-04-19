package com.dominicbrauer.web_wordle_tim24.model;

import java.util.List;

public record GameSession(
  String status,
  int guesses_used,
  String current_guess,
  List<Guess> guesses
) {}