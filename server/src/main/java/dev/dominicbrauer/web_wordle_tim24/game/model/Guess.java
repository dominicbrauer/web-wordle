package dev.dominicbrauer.web_wordle_tim24.game.model;

import java.util.List;

public record Guess(
  String word,  
  boolean was_correct,
  List<Char> character_info
) {}
