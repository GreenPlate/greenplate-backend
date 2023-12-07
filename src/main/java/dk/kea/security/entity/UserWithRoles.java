package dk.kea.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entity class representing a user with roles.
 * <p>
 * This class is mapped to the database table and implements Spring Security's {@link UserDetails}.
 * It includes information about the user, such as username, email, hashed and salted password,
 * creation timestamp, last edited timestamp, and roles assigned to the user.
 * </p>
 * <p>
 * This class uses single-table inheritance with a discriminator column named "DISCRIMINATOR_TYPE".
 * </p>
 * <p>
 * Passwords are hashed and salted using the BCryptPasswordEncoder.
 * </p>
 *
 * @see UserDetails
 */
@Configurable
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISCRIMINATOR_TYPE")
public class UserWithRoles implements UserDetails {

  /*
  This is not very elegant since the password encoder is hardcoded, and eventually could end as being different
  from the one used in the project. It's done this way to make it easier to use this semester, since this class
  hashes and salts passwords automatically. Also, it's done like this since YOU CANNOT inject beans into entities.
  */
  @Transient
  private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  /**
   * The unique identifier for the user.
   */
  @Id
  @Column(nullable = false, length = 50, unique = true, name = "id")
  String username;

  /**
   * The email address of the user.
   */
  @Column(nullable = false, length = 50, unique = true)
  String email;

  /**
   * The hashed and salted password of the user.
   */
  @Column(nullable = false, length = 60)
  String password;

  /**
   * Indicates whether the user is enabled.
   */
  private boolean enabled = true;

  /**
   * The timestamp when the user was created.
   */
  @CreationTimestamp
  private LocalDateTime created;

  /**
   * The timestamp when the user was last edited.
   */
  @UpdateTimestamp
  private LocalDateTime edited;

  /**
   * The roles assigned to the user.
   */
  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "ENUM('USER','ADMIN')")
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "security_role")
  List<Role> roles = new ArrayList<>();

  /**
   * Default constructor required by JPA.
   */
  public UserWithRoles() {
  }

  /**
   * Constructs a {@code UserWithRoles} object with the specified username, password, and email.
   *
   * @param username The username of the user.
   * @param password The password of the user.
   * @param email    The email address of the user.
   */
  public UserWithRoles(String username, String password, String email) {
    this.username = username;
    setPassword(password);
    this.email = email;
  }

  /**
   * Sets the hashed and salted password for the user.
   *
   * @param pw The password to set.
   */
  public void setPassword(String pw) {
    this.password = passwordEncoder.encode(pw);
  }

  /**
   * Retrieves the authorities granted to the user.
   *
   * @return A collection of authorities granted to the user.
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream().map(role -> new SimpleGrantedAuthority(role.toString())).collect(Collectors.toList());
  }

  /**
   * Adds a role to the user.
   *
   * @param roleToAdd The role to add.
   */
  public void addRole(Role roleToAdd) {
    if (!roles.contains(roleToAdd)) {
      roles.add(roleToAdd);
    }
  }

  /**
   * Removes a role from the user.
   *
   * @param roleToRemove The role to remove.
   */
  public void removeRole(Role roleToRemove) {
    if (roles.contains(roleToRemove)) {
      roles.remove(roleToRemove);
    }
  }

  //You can, but are NOT expected to use the fields below
  @Override
  public boolean isAccountNonExpired() {
    return enabled;
  }

  @Override
  public boolean isAccountNonLocked() {
    return enabled;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return enabled;
  }
}
