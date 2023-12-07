package dk.kea.security.entity;

/**
 * Enum representing user roles in the system.
 * <p>
 * Add required roles for your project to this enum.
 * If you change anything here, you must also change this annotation,
 * used on roles in UserWithRoleController accordingly.
 * <pre>
 * {@code
 * @Column(columnDefinition = "ENUM('USER','ADMIN')")
 * }
 * </pre>
 * </p>
 * <p>
 * The roles defined in this enum are 'USER' and 'ADMIN'.
 * </p>
 * <p>
 * Additionally, a utility method {@link #fromString(String)} is provided
 * to convert a role string to the corresponding {@code Role} enum value.
 * </p>
 *
 * @see UserWithRoles
 */
public enum Role {
    /**
     * Represents the 'USER' role.
     */
    USER,

    /**
     * Represents the 'ADMIN' role.
     */
    ADMIN;

    /**
     * Converts a role string to the corresponding {@code Role} enum value.
     *
     * @param roleString The role string to convert.
     * @return The corresponding {@code Role} enum value.
     */
    public static Role fromString(String roleString) {
        return Role.valueOf(roleString.trim().toUpperCase());
    }
}
