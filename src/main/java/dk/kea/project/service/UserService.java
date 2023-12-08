package dk.kea.project.service;

import dk.kea.project.dto.UserRequest;
import dk.kea.project.dto.UserResponse;
import dk.kea.project.entity.User;
import dk.kea.project.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service class responsible for managing operations related to users.
 * This service provides methods for retrieving, updating, creating, and deleting user information.
 *
 *
 */
@Service
public class UserService {
    UserRepository userRepository;
    /**
     * Constructs a new UserService with the specified UserRepository.
     *
     * @param userRepository The repository for managing User entities.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /**
     * Retrieves a list of UserResponse objects representing all users.
     *
     * @return A list of UserResponse objects representing all users in the system.
     */
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new UserResponse(user)).toList();
    }
    /**
     * Retrieves a UserResponse object based on the provided username.
     *
     * @param username The username used to retrieve user information.
     * @return A UserResponse object representing the user with the specified username.
     */
    public UserResponse getUserById(String username) {
        User user = getUser(username);
        return new UserResponse(user);
    }

    private User getUser(String username) {
        return userRepository.findById(username).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
    /**
     * Updates user information based on the provided username and UserRequest.
     *
     * @param username    The username of the user to be updated.
     * @param userRequest The UserRequest containing information to update.
     * @return A ResponseEntity<Boolean> indicating the success of the update operation.
     */
    public ResponseEntity<Boolean> updateUser(String username, UserRequest userRequest) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = getUser(username);
        if(!userRequest.getUsername().equals(username)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot change username");
        }
        user.setPassword(userRequest.getPassword());
        if(!passwordEncoder.matches(user.getPassword(), userRequest.getPassword())){
            user.setPassword(userRequest.getPassword());
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords are the same");
        }
        user.setEmail(userRequest.getEmail());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        userRepository.save(user);
        return ResponseEntity.ok(true);
    }
    /**
     * Deletes a user based on the provided username.
     *
     * @param username The username of the user to be deleted.
     */
    public void deleteUserByUser(String username) {
        //TODO: Create guard statement
        User user = getUser(username);
        userRepository.deleteById(username);
    }
    /**
     * Creates a new user based on the provided UserRequest.
     *
     * @param userRequest The UserRequest containing information to create a new user.
     * @return A UserResponse object representing the newly created user.
     */
    public UserResponse createUser(UserRequest userRequest) {
        User user = UserRequest.getUserEntity(userRequest);

        userRepository.findById(user.getUsername()).ifPresent(u -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        });

        if(userRepository.findByEmail(user.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        };

        userRepository.save(user);
        return new UserResponse(user);
    }
    /**
     * Deletes a user based on the provided username.
     *
     * @param username The username of the user to be deleted.
     */
    public void deleteUser(String username) {
        User user = getUser(username);
        userRepository.deleteById(username);
    }

    public UserResponse patchUser(UserRequest userRequest, String name) {
        User existingUser = getUser(name);
        if(!userRequest.getUsername().equals(name)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not the same username");
        }
        else {
            if (userRequest.getPassword() != null) existingUser.setPassword(userRequest.getPassword());
            if (userRequest.getEmail() != null) existingUser.setEmail(userRequest.getEmail());
            if (userRequest.getFirstName() != null) existingUser.setFirstName(userRequest.getFirstName());
            if (userRequest.getLastName() != null) existingUser.setLastName(userRequest.getLastName());
        }
        User updatedUser = userRepository.save(existingUser);
        return new UserResponse(updatedUser);
    }
}
