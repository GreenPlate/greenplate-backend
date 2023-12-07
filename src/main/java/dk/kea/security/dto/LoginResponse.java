package dk.kea.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing a login response.
 * This class encapsulates the information returned to the client after a successful login.
 * It includes the username, authentication token, and a list of roles associated with the user.
 *
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {

  /**
   * The username associated with the login response.
   */
  private String username;

  /**
   * The authentication token generated after a successful login.
   */
  private String token;

  /**
   * A list of roles associated with the user.
   */
  private List<String> roles;

  /**
   * Constructs a new {@code LoginResponse} with the specified parameters.
   *
   * @param userName The username associated with the login response.
   * @param token The authentication token generated after a successful login.
   * @param roles A list of roles associated with the user.
   */
  public LoginResponse(String userName, String token, List<String> roles) {
    this.username = userName;
    this.token = token;
    this.roles = roles;
  }
}
