package com.dominicbrauer.web_wordle_tim24.model;

import java.util.List;

public record ClassicGame(
  String status,
  int game_index,
  List<GameSession> games,
  List<List<Integer>> scores
) {}
