package dev.dominicbrauer.web_wordle_tim24.auth.utility;

import org.mindrot.jbcrypt.BCrypt;

/**
 * A utility for common authentication methods.
 */
public class PasswordEncryption {

  private static final String SALT = "$2a$10$GvalEO8g464bMiGLfZW0re";

  /**
   * Hashes a password using BCrypt.
   * @param password - The password to hash.
   * @return The hashed password
   */
  public static String hashPassword(String password) {
    return BCrypt.hashpw(password, SALT);
  }

}
