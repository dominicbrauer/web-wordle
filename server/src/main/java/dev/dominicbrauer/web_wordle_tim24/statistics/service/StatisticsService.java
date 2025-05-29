package dev.dominicbrauer.web_wordle_tim24.statistics.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.dominicbrauer.web_wordle_tim24.game.model.Game;
import dev.dominicbrauer.web_wordle_tim24.game.model.GameSession;
import dev.dominicbrauer.web_wordle_tim24.statistics.model.StatisticsEntity;
import dev.dominicbrauer.web_wordle_tim24.statistics.repository.StatisticsH2Repository;

@Service
public class StatisticsService {

	@Autowired
	StatisticsH2Repository statisticsRepository;

	/**
	 * Initializes new statistics for a user.
	 * @param userId
	 * @return the saved StatisticsEntity
	 */
	public StatisticsEntity initStatistics(Long userId) {
		return statisticsRepository.save(new StatisticsEntity(
			userId,
			0L,
			0,
			0,
			0
		));
	}

	/**
	 * Gets the StatisticsEntity by a userID.
	 * @param userId
	 * @return the StatisticsEntity if found
	 */
	public Optional<StatisticsEntity> getByUserId(Long userId) {
		return statisticsRepository.findById(userId);
	}

	/**
	 * Updates the statistics of a user based on a gameSession.
	 * @param userId
	 * @param gameSession the latest gameSession object
	 * @return the updated StatisticsEntity
	 */
	public StatisticsEntity updateStatistics(Long userId, GameSession gameSession) {
		StatisticsEntity stats = getByUserId(userId).get();

		stats.setHighestGameReached(updateHighestGameReached(stats.getHighestGameReached(), gameSession.gameIndex()));
		stats.setHighestScoreTotal(updateHighestScoreTotal(stats.getHighestScoreTotal(), gameSession));
		stats.setHighestScoreGame(updateHighestScoreGame(stats.getHighestScoreGame(), gameSession));
		stats.setTotalGamesPlayed(stats.getTotalGamesPlayed() + 1);

		return statisticsRepository.save(stats);
	}

	/**
	 * Updates the highestGameReached property.
	 * @param current the current highscore
	 * @param follower the potential new highscore
	 * @return which one is higher
	 */
	public int updateHighestGameReached(int current, int follower) {
		return follower > current ? follower : current;
	}

	/**
	 * Updates the highestScoreTotal property.
	 * @param current the current highscore
	 * @param gameSession the potential new highscore
	 * @return which one is higher
	 */
	public Long updateHighestScoreTotal(Long current, GameSession gameSession) {
		Long follower = 0L;
		for (Game game : gameSession.games()) {
			follower += game.finalScore();
		}
		return follower > current ? follower : current;
	}

	/**
	 * Updates the highestScoreGame property.
	 * @param current the current highscore
	 * @param gameSession the potential new highscore
	 * @return which one is higher
	 */
	public int updateHighestScoreGame(int current, GameSession gameSession) {
		int follower = 0;
		for (Game game : gameSession.games()) {
			if (game.finalScore() > follower) {
				follower = game.finalScore();
			}
		}
		return follower > current ? follower : current;
	}
	
}
