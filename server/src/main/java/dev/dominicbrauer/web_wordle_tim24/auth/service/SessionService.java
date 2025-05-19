package dev.dominicbrauer.web_wordle_tim24.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.dominicbrauer.web_wordle_tim24.auth.model.SessionEntity;
import dev.dominicbrauer.web_wordle_tim24.auth.repository.SessionH2Repository;

@Service
public class SessionService {

  @Autowired
  private SessionH2Repository sessionRepository;
  
  public SessionEntity addSession(SessionEntity user) {
    return sessionRepository.save(user);
  }

  public List<SessionEntity> getAllSessions() {
    return sessionRepository.findAll();
  }
  
}

