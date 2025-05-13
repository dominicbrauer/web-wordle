package dev.dominicbrauer.web_wordle_tim24.model.auth;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Session {

  @Id // Primary key
  private Long id;

  private String loginTime;
  private UUID sessionId;

  public UUID getSessionId() {
    return this.sessionId;
  }

  /**
   * Random sessionId in UUID-format
   */
  public void setSessionId() {
    this.sessionId = UUID.randomUUID();
  }
  
}
