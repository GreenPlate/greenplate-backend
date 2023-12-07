package dk.kea.security.api;

import dk.kea.security.dto.UserWithRolesRequest;
import dk.kea.security.dto.UserWithRolesResponse;
import dk.kea.security.entity.Role;
import dk.kea.security.service.UserWithRolesService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * RESTful controller for handling user-related operations with roles.
 * This controller provides endpoints for adding users with roles and modifying roles for existing users.
 * Restricted access is applied to certain endpoints based on the user's authority.
 *
 *
 */
@RestController
@RequestMapping("/api/user-with-role")
public class UserWithRoleController {

  /**
   * The default role to be assigned to new users if not specified.
   * Set to null if no role should be added by default.
   */
  static Role DEFAULT_ROLE_TO_ASSIGN = Role.USER;

  UserWithRolesService userWithRolesService;
  /**
   * Constructs a new UserWithRoleController with the specified UserWithRolesService.
   *
   * @param userWithRolesService The service handling user-related operations with roles.
   */
  public UserWithRoleController(UserWithRolesService userWithRolesService) {
    this.userWithRolesService = userWithRolesService;
  }
  /**
   * Endpoint for adding a user with roles.
   * Anonymous users can call this endpoint, and a default role is assigned if specified.
   *
   * @param request The UserWithRolesRequest containing user information and roles.
   * @return A UserWithRolesResponse representing the added user with roles.
   */
  //Anonymous users can call this. Set DEFAULT_ROLE_TO_ASSIGN to null if no role should be added
  @PostMapping
  public UserWithRolesResponse addUserWithRoles(@RequestBody UserWithRolesRequest request) {
    return userWithRolesService.addUserWithRoles (request, DEFAULT_ROLE_TO_ASSIGN);
  }
  /**
   * Endpoint for adding a role to an existing user.
   * Restricted access: Only users with 'ADMIN' authority can call this endpoint.
   *
   * @param username The username of the user to add the role to.
   * @param role     The role to be added.
   * @return A UserWithRolesResponse representing the updated user with roles.
   */
  //Take care with this. This should NOT be something everyone can call
  @PreAuthorize("hasAuthority('ADMIN')")
  @PatchMapping("/add-role/{username}/{role}")
  public UserWithRolesResponse addRole(@PathVariable String username, @PathVariable String role) {
    return userWithRolesService.addRole(username, Role.fromString(role));
  }
  /**
   * Endpoint for removing a role from an existing user.
   * Restricted access: Only users with 'ADMIN' authority can call this endpoint.
   *
   * @param username The username of the user to remove the role from.
   * @param role     The role to be removed.
   * @return A UserWithRolesResponse representing the updated user with roles.
   */
  //Take care with this. This should NOT be something everyone can call
  @PreAuthorize("hasAuthority('ADMIN')")
  @PatchMapping("/remove-role/{username}/{role}")
  public UserWithRolesResponse removeRole(@PathVariable String username, @PathVariable String role) {
    return userWithRolesService.removeRole(username, Role.fromString(role));
  }
}
