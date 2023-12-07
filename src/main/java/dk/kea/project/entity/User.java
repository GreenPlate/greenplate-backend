package dk.kea.project.entity;

import dk.kea.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a user.
 * <p>
 * This class is mapped to the database table and is used to store information
 * about users, including first name, last name, and inherited properties from {@link UserWithRoles}.
 * </p>
 * <p>
 * This class uses single-table inheritance with a discriminator column named "USER_TYPE".
 * </p>
 *
 * @see UserWithRoles
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class User extends UserWithRoles {

    /**
     * The first name of the user.
     */
    @Column(nullable = false, length = 55)
    private String firstName;

    /**
     * The last name of the user.
     */
    @Column(nullable = false, length = 55)
    private String lastName;

    /**
     * Constructs a {@code User} object with the specified properties.
     *
     * @param username  The username of the user.
     * @param email     The email address of the user.
     * @param password  The password of the user.
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     */
    public User(String username, String email, String password, String firstName, String lastName) {
        super(username, password, email);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
