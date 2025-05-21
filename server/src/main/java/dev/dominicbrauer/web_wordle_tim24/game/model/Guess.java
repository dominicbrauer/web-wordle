package dev.dominicbrauer.web_wordle_tim24.game.model;

import java.util.ArrayList;

public record Guess(
	String word,
	boolean was_correct,
	ArrayList<Char> character_info
) {}
