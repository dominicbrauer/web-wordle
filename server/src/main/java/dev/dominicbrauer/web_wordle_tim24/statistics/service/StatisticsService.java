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
	 * 
	 * @param userId
	 * @return
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
	 * 
	 * @return
	 */
	public Optional<StatisticsEntity> getByUserId(Long userId) {
		return statisticsRepository.findById(userId);
	}

	/**
	 * 
	 * @param userId
	 * @return
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
	 * 
	 * @param current
	 * @param follower
	 * @return
	 */
	public int updateHighestGameReached(int current, int follower) {
		return follower > current ? follower : current;
	}

	/**
	 * 
	 * @param current
	 * @param gameSession
	 * @return
	 */
	public Long updateHighestScoreTotal(Long current, GameSession gameSession) {
		Long follower = 0L;
		for (Game game : gameSession.games()) {
			follower += game.finalScore();
		}
		return follower > current ? follower : current;
	}

	/**
	 * 
	 * @param current
	 * @param gameSession
	 * @return
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
