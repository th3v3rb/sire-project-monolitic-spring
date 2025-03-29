package com.dantesoft.siremono.modules.auth.store.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dantesoft.siremono.modules.auth.store.entity.User;
import com.dantesoft.siremono.modules.auth.store.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
  private final UserRepository userRepo;
  private final JwtService jwtService;

  public Optional<User> findById(UUID id) {
    return userRepo.findById(id);
  }

  /**
   * Return user from back session
   *
   * @return
   */
  public User getAuthenticatedUser() {
    return Optional
        .ofNullable(SecurityContextHolder.getContext().getAuthentication())
        .filter(Authentication::isAuthenticated)
        .map(Authentication::getPrincipal)
        .filter(principal -> principal instanceof User)
        .map(principal -> (User) principal).orElse(null);
  }

  /**
   * @param email
   * @return
   */
  public Optional<User> findByEmail(String email) {
    return this.userRepo.findByEmail(email);
  }

  /**
   * @param request create user request #{@see CreateUserRequest.java}
   * @return the operation status
   */
  public User save(User user) {
    return userRepo.save(user);
  }

  /**
   * Extract the user information from a JWT token
   *
   * @param urlToken token
   * @return user information
   */
  public User extractUserInformationFrom(String urlToken) {
    var user = new User();
    user.setId(jwtService.extractUserId(urlToken));
    user.setUsername(jwtService.extractUsername(urlToken));
    user.setEmail(jwtService.extractEmail(urlToken));
    user.setRoles(jwtService.extractRoles(urlToken));
    user.setEmailVerifiedAt(jwtService.extractMailVerifiedAt(urlToken));

    return user;
  }

}
