package com.dominicbrauer.web_wordle_tim24.model;

import java.util.List;

public record Guess(
  String guess,  
  boolean was_correct,
  List<Char> character_info
) {}
