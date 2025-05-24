package dev.dominicbrauer.web_wordle_tim24.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.dominicbrauer.web_wordle_tim24.auth.model.SignUpRequest;
import dev.dominicbrauer.web_wordle_tim24.auth.model.UserEntity;
import dev.dominicbrauer.web_wordle_tim24.auth.repository.UserH2Repository;
import dev.dominicbrauer.web_wordle_tim24.auth.utility.PasswordEncryption;

@Service
public class AuthService {

  @Autowired
  private UserH2Repository userRepository;

  /**
   * 
   */
  public UserEntity addUser(SignUpRequest signupForm) {
    UserEntity userEntity = new UserEntity(
      signupForm.name(),
      signupForm.email(),
      PasswordEncryption.hashPassword(signupForm.password())
    );

    return userRepository.save(userEntity);
  }

  /**
   * 
   */
  public Optional<UserEntity> getUserById(Long userId) {
    return userRepository.findById(userId);
  }

  /**
   * 
   */
  public List<UserEntity> getAllUsers() {
    return userRepository.findAll();
  }

  /**
   * Compares two passwords for equality.
   * @param pw1 first password
   * @param pw2 second password
   * @return true if passwords are equal
   */
  public boolean comparePasswords(String pw1, String pw2) {
    return pw1.equals(pw2);
  }

  /**
   * Validates a string for correct email formatting.
   * @param email an email string
   * @return true if email is valid
   */
  public boolean validEmail(String email) {
    String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    return email.matches(EMAIL_REGEX);
  }

  /**
   * Gets a user from the database by email.
   * @param email the email string
   * @return Optional object containing empty or a UserEntity
   */
  public Optional<UserEntity> userByEmail(String email) {
    return userRepository.findByEmail(email);
  }
  
}