package dev.dominicbrauer.web_wordle_tim24.game.model;

import java.util.ArrayList;

public record Guess(
	String word,
	boolean wasCorrect,
	ArrayList<Char> characterInfo
) {}
