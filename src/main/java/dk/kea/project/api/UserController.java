package dk.kea.project.api;

import dk.kea.project.dto.UserRequest;
import dk.kea.project.dto.UserResponse;
import dk.kea.project.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
/**
 * REST controller handling user-related endpoints.
 * <p>
 * This controller provides endpoints for managing user entities, including retrieving all users,
 * getting user details, updating user information, creating users, and deleting users.
 * It interacts with the User Service to fulfill its functionalities.
 * </p>
 *
 *
 */

@RestController
@RequestMapping("/api/users")
public class UserController {
    UserService userService;
    /**
     * Constructor to inject the UserService dependency.
     *
     * @param userService The UserService instance to be injected.
     */

    public UserController(UserService userService) {
        this.userService = userService;
    }
    /**
     * Retrieves a list of all users (Admin endpoint).
     *
     * @return A list of {@code UserResponse} representing all users.
     */
    //Admin
    @GetMapping()
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }
    /**
     * Retrieves details of the authenticated user.
     *
     * @param principal The Principal object representing the authenticated user.
     * @return A {@code UserResponse} representing the details of the authenticated user.
     */
    //User
    @GetMapping("/user-as-authenticated")
    public UserResponse getUserById(Principal principal){
        return userService.getUserById(principal.getName());
    }
    /**
     * Updates information of the authenticated user.
     *
     * @param principal    The Principal object representing the authenticated user.
     * @param userRequest  The {@code UserRequest} containing updated user information.
     * @return A {@code ResponseEntity<Boolean>} indicating the success of the update operation.
     */
    //User
    @PutMapping("/user-as-authenticated")
    public ResponseEntity<Boolean> updateUser(Principal principal, @RequestBody UserRequest userRequest){
        return userService.updateUser(principal.getName(), userRequest);
    }
      /**
      * Deletes the authenticated user.
      *
      * @param principal The Principal object representing the authenticated user.
      */
    //User
    @DeleteMapping("/user-as-authenticated")
    public void deleteUserByUser(Principal principal){
        userService.deleteUserByUser(principal.getName());
    }
    /**
     * Creates a new user (Anonymous endpoint).
     *
     * @param userRequest The {@code UserRequest} containing user information for creation.
     * @return A {@code UserResponse} representing the details of the created user.
     */
    //Anonymous
    @PostMapping()
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        return userService.createUser(userRequest);
    }
    /**
     * Deletes a user by username (Admin endpoint).
     *
     * @param username The username of the user to be deleted.
     */
    //Admin
    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username){
        userService.deleteUser(username);
    }
    @PatchMapping("/user-as-authenticated")
    public UserResponse patchUser(@RequestBody UserRequest userRequest, Principal principal){
        return userService.patchUser(userRequest, principal.getName());
    }
}
