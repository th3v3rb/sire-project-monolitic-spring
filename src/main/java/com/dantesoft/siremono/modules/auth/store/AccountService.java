package com.dantesoft.siremono.modules.auth.store;

import java.util.Optional;
import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.dantesoft.siremono.modules.auth.AuthErrors;
import com.dantesoft.siremono.modules.auth.store.entity.AccountEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
  private final AccountRepository repository;
  private final JwtService jwtService;

  public Optional<AccountEntity> findById(UUID id) {
    return repository.findById(id);
  }

  public AccountEntity getAuthenticatedUser() {
    return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
        .filter(Authentication::isAuthenticated).map(Authentication::getPrincipal)
        .filter(principal -> principal instanceof UserDetails)
        .map(principal -> (AccountEntity) principal)
        .orElseThrow(() -> new UsernameNotFoundException("User not authenticated"));
  }

  public Optional<AccountEntity> findByEmail(String email) {
    return repository.findByEmail(email);
  }

  public AccountEntity findByEmailOrFail(String email) {
    return repository.findByEmail(email)
        .orElseThrow(() -> new AuthErrors.NotFoundException("User not found"));
  }

  public AccountEntity save(AccountEntity user) {
    return repository.save(user);
  }

  public AccountEntity extractUserInformationFrom(String urlToken) {
    var user = new AccountEntity();
    user.setId(jwtService.extractUserId(urlToken));
    user.setUsername(jwtService.extractUsername(urlToken));
    user.setEmail(jwtService.extractEmail(urlToken));
    user.setRoles(jwtService.extractRoles(urlToken));
    user.setEmailVerifiedAt(jwtService.extractMailVerifiedAt(urlToken));

    return user;
  }

}
