package dk.kea.security.api;

import dk.kea.security.service.UserDetailsServiceImp;
import dk.kea.security.dto.LoginRequest;
import dk.kea.security.dto.LoginResponse;
import dk.kea.security.entity.UserWithRoles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
/**
 * RESTful controller for handling authentication-related operations.
 * This controller provides an endpoint for user login and token generation.
 * It uses an AuthenticationManager and JwtEncoder for user authentication and token creation.
 *
 */
@RestController
@RequestMapping("/api/auth/")
@CrossOrigin
public class AuthenticationController {

  @Value("${app.token-issuer}")
  private String tokenIssuer;

  @Value("${app.token-expiration}")
  private long tokenExpiration;

  private AuthenticationManager authenticationManager;

  JwtEncoder encoder;
  /**
   * Constructs a new AuthenticationController with the specified AuthenticationManager and JwtEncoder.
   *
   * @param authenticationManager The manager for handling user authentication.
   * @param encoder               The encoder for generating JWT tokens.
   */
  public AuthenticationController(AuthenticationManager authenticationManager, JwtEncoder encoder) {
    this.authenticationManager = authenticationManager;
    this.encoder = encoder;
  }
  /**
   * Endpoint for user login and token generation.
   * Authenticates the user using the provided credentials and generates a JWT token on successful authentication.
   *
   * @param request The LoginRequest containing user login credentials.
   * @return A ResponseEntity containing a LoginResponse with user information and the generated token.
   * @throws ResponseStatusException If authentication fails due to bad credentials, returns UNAUTHORIZED status.
   */
  @PostMapping("login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

    try {
      UsernamePasswordAuthenticationToken uat = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
      Authentication authentication = authenticationManager.authenticate(uat);

      UserWithRoles user = (UserWithRoles) authentication.getPrincipal();
      Instant now = Instant.now();
      long expiry = tokenExpiration;
      String scope = authentication.getAuthorities().stream()
              .map(GrantedAuthority::getAuthority)
              .collect(joining(" "));

      JwtClaimsSet claims = JwtClaimsSet.builder()
              .issuer(tokenIssuer)  //Only this for simplicity
              .issuedAt(now)
              .expiresAt(now.plusSeconds(tokenExpiration))
              .subject(user.getUsername())
              .claim("roles", scope)
              .build();
      JwsHeader jwsHeader = JwsHeader.with(() -> "HS256").build();
      String token = encoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();


      List<String> roles = user.getRoles().stream().map(role -> role.toString()).collect(Collectors.toList());
      return ResponseEntity.ok()
              .body(new LoginResponse(user.getUsername(), token, roles));

    } catch (BadCredentialsException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, UserDetailsServiceImp.WRONG_USERNAME_OR_PASSWORD);
    }

  }
}
