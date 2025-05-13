package dev.dominicbrauer.web_wordle_tim24.model.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserEntity {

  @Id // Primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
  private Long id;

  private String userName;
  private String email;
  private String password;
  private String profilePicturePath;

}
