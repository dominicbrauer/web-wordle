package com.dominicbrauer.web_wordle_tim24.model;

import java.util.List;

public record GameSession(
  String status,
  String word,
  int guesses_used,
  List<Guess> guesses
) {}
