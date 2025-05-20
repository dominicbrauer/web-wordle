package dev.dominicbrauer.web_wordle_tim24.auth.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.dominicbrauer.web_wordle_tim24.auth.model.SessionEntity;
import dev.dominicbrauer.web_wordle_tim24.auth.repository.SessionH2Repository;

@Service
public class SessionService {

  @Autowired
  private SessionH2Repository sessionRepository;
  
  /**
   * 
   */
  public SessionEntity createSession(Long userId) {
    UUID token = UUID.randomUUID();

    SessionEntity session = new SessionEntity(token, userId, 6969L);

    return sessionRepository.save(session);
  }

  /**
   * 
   */
  public List<SessionEntity> getAllSessions() {
    return sessionRepository.findAll();
  }
  
}

