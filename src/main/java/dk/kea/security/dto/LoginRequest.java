package dk.kea.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a login request.
 * This class encapsulates the user login credentials, including the username and password.
 * It is used to transfer this information between the client and server during the authentication process.
 *
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

   /**
    * The username associated with the login request.
    */
   private String username;

   /**
    * The password associated with the login request.
    */
   private String password;
}
