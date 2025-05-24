package dev.dominicbrauer.web_wordle_tim24.statistics.model;

public record Statistics(
	Long highestScoreTotal,
	int highestGameReached,
	int highestScoreGame,
	int totalGamesPlayed
) {}
