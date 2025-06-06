package dev.dominicbrauer.web_wordle_tim24.auth.model;

import dev.dominicbrauer.web_wordle_tim24.statistics.model.Statistics;

public record User(
	String name,
	String email,
	Statistics stats
) {}
