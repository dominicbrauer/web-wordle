package dev.dominicbrauer.web_wordle_tim24.statistics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Statistics")
public class StatisticsEntity {

	@Id
	private Long userId;

	private Long highestScoreTotal;
	private int highestGameReached;
	private int highestScoreGame;
	private int totalGamesPlayed;
	// averageGuessesNeeded
	// favoriteWord

	public StatisticsEntity(
		Long userId,
		Long highestScoreTotal,
		int highestGameReached,
		int highestScoreGame,
		int totalGamesPlayed
	) {
		this.userId = userId;
		this.highestScoreTotal = highestScoreTotal;
		this.highestGameReached = highestGameReached;
		this.highestScoreGame = highestScoreGame;
		this.totalGamesPlayed = totalGamesPlayed;
	}

}
