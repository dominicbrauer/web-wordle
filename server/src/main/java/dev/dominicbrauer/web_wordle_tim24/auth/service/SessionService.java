package dev.dominicbrauer.web_wordle_tim24.auth.service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.dominicbrauer.web_wordle_tim24.auth.model.SessionEntity;
import dev.dominicbrauer.web_wordle_tim24.auth.repository.SessionH2Repository;

@Service
public class SessionService {

	@Autowired
	private SessionH2Repository sessionRepository;

	private final Long WEEK = 1000 * 60 * 60 * 24 * 7L;

	/**
	 * Creates a new session for the given user.
	 * @param userId the user ID the session should be assigned to
	 * @return the saved SessionEntity 
	 */
	public SessionEntity createSession(Long userId) {
		UUID sessionToken = UUID.randomUUID();

		Long currentTime = new Date().getTime();
		Long expireDate = currentTime + WEEK;

		SessionEntity session = new SessionEntity(sessionToken, userId, expireDate);
		return sessionRepository.save(session);
	}

	/**
	 * 
	 */
	public Optional<SessionEntity> findSession(String cookieValue) {
		try {
			UUID sessionToken = UUID.fromString(cookieValue);
			return sessionRepository.findBySessionToken(sessionToken);
		}
		catch (Exception e) {
			return Optional.empty();
		}
	}

	/**
	 * 
	 * @param cookieValue
	 */
	public void invalidateSession(String cookieValue) {
		sessionRepository.delete(findSession(cookieValue).get());
	}
}

