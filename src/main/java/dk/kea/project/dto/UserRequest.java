package dk.kea.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dk.kea.project.entity.User;
import lombok.*;

/**
 * Data Transfer Object (DTO) representing a request to create or update a user.
 * <p>
 * This class is used to transfer user-related data in requests to create or update user information.
 * It includes details such as the username, password, email, first name, and last name.
 * </p>
 * <p>
 * The class is annotated with {@link Getter}, {@link Setter}, {@link AllArgsConstructor}, and
 * {@link JsonInclude} from the Lombok and Jackson libraries for automatic generation of getters,
 * setters, a constructor with all arguments, and JSON inclusion configuration.
 * </p>
 * <p>
 * The method {@code getUserEntity(UserRequest userRequest)} is a static method that converts
 * a {@link UserRequest} object into a {@link User} entity.
 * </p>
 *

 */
@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequest {

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * The first name of the user.
     */
    private String firstName;

    /**
     * The last name of the user.
     */
    private String lastName;

    /**
     * Static method that converts a {@link UserRequest} object into a {@link User} entity.
     *
     * @param userRequest The UserRequest object to convert.
     * @return The corresponding User entity.
     */
    public static User getUserEntity(UserRequest userRequest) {
        return new User(userRequest.username,
              userRequest.email,
              userRequest.password,
              userRequest.firstName,
              userRequest.lastName);
    }
}
