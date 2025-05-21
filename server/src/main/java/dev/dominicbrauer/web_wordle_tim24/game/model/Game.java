package dev.dominicbrauer.web_wordle_tim24.game.model;

import java.util.ArrayList;

public record Game(
	String status,
	int guesses_used,
	String current_guess,
	boolean current_guess_valid,
	ArrayList<Guess> guesses,
	int final_score
) {}
