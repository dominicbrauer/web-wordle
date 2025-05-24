package dev.dominicbrauer.web_wordle_tim24.game.model;

import java.util.ArrayList;

public record Game(
	int guessesUsed,
	String currentGuess,
	boolean currentGuess_valid,
	ArrayList<Guess> guesses,
	int finalScore
) {}
