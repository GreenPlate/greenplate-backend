package dk.kea.security.dto;

import lombok.*;

/**
 * Data Transfer Object (DTO) representing a user request with roles.
 * This class encapsulates the information required when creating a new user along with their roles.
 *
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserWithRolesRequest {

    /**
     * The username associated with the user request.
     */
    @NonNull
    String username;

    /**
     * The password associated with the user request.
     */
    @NonNull
    String password;

    /**
     * The email associated with the user request.
     */
    @NonNull
    String email;
}
