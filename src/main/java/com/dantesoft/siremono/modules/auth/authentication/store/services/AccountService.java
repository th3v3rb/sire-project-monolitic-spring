package com.dantesoft.siremono.modules.auth.authentication.store.services;

import com.dantesoft.siremono.modules.auth.authentication.store.entity.AccountEntity;
import com.dantesoft.siremono.modules.auth.authentication.store.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
  private final AccountRepository repository;

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
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  public AccountEntity save(AccountEntity user) {
    return repository.save(user);
  }

  public List<AccountEntity> findAll() {
    return repository.findAll();
  }

}
