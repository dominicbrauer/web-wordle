package dev.dominicbrauer.web_wordle_tim24.game.model;

import java.util.ArrayList;

public record GameSession(
	String status,
	int gameIndex,
	ArrayList<Game> games
) {}
