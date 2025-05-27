package dev.dominicbrauer.web_wordle_tim24.auth.model;

import java.util.UUID;

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
@Table(name = "Sessions")
public class SessionEntity {

	@Id
	private UUID sessionToken;

	private Long userId;
	private Long expireTime;

	public SessionEntity(UUID sessionToken, Long userId, Long expireTime) {
		this.sessionToken = sessionToken;
		this.userId = userId;
		this.expireTime = expireTime;
	}

}
