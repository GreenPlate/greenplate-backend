package dk.kea.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dk.kea.project.entity.User;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing a response containing user information.
 * <p>
 * This class is used to transfer user-related data in responses. It includes details
 * such as the username, email, first name, last name, creation timestamp, and last edit timestamp.
 * </p>
 * <p>
 * The class is annotated with {@link Getter}, {@link Setter}, and {@link JsonInclude}
 * from the Lombok and Jackson libraries for automatic generation of getters, setters, and JSON inclusion
 * configuration. Additionally, the {@link JsonFormat} annotation is used to specify the date-time format
 * for the 'created' and 'edited' fields.
 * </p>
 * <p>
 * The constructor {@code UserResponse(User user)} initializes the fields of the DTO with data from
 * a {@link User} entity.
 * </p>
 *
 *
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    /**
     * The username of the user.
     */
    private String username;

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
     * The timestamp when the user was created.
     */
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;

    /**
     * The timestamp when the user was last edited.
     */
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime edited;

    /**
     * Constructor that initializes the fields of the DTO with data from a {@link User} entity.
     *
     * @param user The User entity from which to extract data.
     */
    public UserResponse(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.created = user.getCreated();
        this.edited = user.getEdited();
    }
}
