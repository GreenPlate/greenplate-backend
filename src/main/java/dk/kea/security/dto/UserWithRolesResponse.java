package dk.kea.security.dto;

import lombok.Getter;
import lombok.Setter;
import dk.kea.security.entity.UserWithRoles;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Data Transfer Object (DTO) representing a user response with roles.
 * This class encapsulates the information to be sent in response to requests involving user details and roles.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-01-01
 */
@Getter
@Setter
public class UserWithRolesResponse {

    /**
     * The username associated with the user response.
     */
    String userName;

    /**
     * The role names associated with the user response.
     */
    List<String> roleNames;

    /**
     * The email associated with the user response.
     */
    String email;

    /**
     * Constructs a UserWithRolesResponse object based on a UserWithRoles entity.
     *
     * @param userWithRoles The UserWithRoles entity from which to construct the response.
     */
    public UserWithRolesResponse(UserWithRoles userWithRoles) {
        this.userName = userWithRoles.getUsername();
        this.roleNames = userWithRoles.getRoles().stream().map(role -> role.toString()).collect(Collectors.toList());
        this.email = userWithRoles.getEmail();
    }
}
