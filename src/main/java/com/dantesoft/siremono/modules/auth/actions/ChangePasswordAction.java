package com.dantesoft.siremono.modules.auth.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.auth.store.AccountService;
import com.dantesoft.siremono.modules.auth.store.entity.AccountEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Action for changing user password
 */
@Slf4j
@RequiredArgsConstructor
public class ChangePasswordAction extends AbstractCommand<ChangePasswordInput, ChangePasswordOutput> {
  private final AccountService accountService;
  private final PasswordEncoder passwordEncoder;
  
  @Override
  protected ChangePasswordOutput doExecute() {
    var currentUser = accountService.getAuthenticatedUser();
    var account = accountService.findById(currentUser.getId())
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    
    validatePasswords(account);
    updatePassword(account);
    
    log.info("Password changed successfully for user: {}", account.getUsername());
    return new ChangePasswordOutput();
  }
  
  /**
   * Validates that the current password is correct and the new passwords match
   */
  private void validatePasswords(AccountEntity account) {
    if (!getInput().getUpdatedPassword().equals(getInput().getConfirmationPassword())) {
      log.warn("Password mismatch attempt for user: {}", account.getUsername());
      throw new IllegalArgumentException("New password and confirmation password don't match");
    }
    
    if (!passwordEncoder.matches(getInput().getCurrentPassword(), account.getPassword())) {
      log.warn("Invalid current password attempt for user: {}", account.getUsername());
      throw new IllegalArgumentException("Current password is incorrect");
    }
    
    if (passwordEncoder.matches(getInput().getUpdatedPassword(), account.getPassword())) {
      log.warn("Same password attempt for user: {}", account.getUsername());
      throw new IllegalArgumentException("New password must be different from current password");
    }
  }
  
  /**
   * Updates the user's password in the database
   */
  private void updatePassword(AccountEntity account) {
    String newEncodedPassword = passwordEncoder.encode(getInput().getUpdatedPassword());
    account.setPassword(newEncodedPassword);
    accountService.save(account);
  }
}