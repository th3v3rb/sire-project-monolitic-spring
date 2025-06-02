package com.dantesoft.siremono.modules.auth.authentication.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.modules.auth.authentication.store.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class DisableAccountAction extends AbstractCommand<DisableAccountInput, DisableAccountOutput> {
  private final AccountService accountService;
  private final PasswordEncoder encoder;

  @Override
  protected DisableAccountOutput doExecute() {
    var user = accountService.getAuthenticatedUser();
    var fullUser = accountService.findByEmailOrFail(user.getEmail());

    var equals = encoder.matches(getInput().getPassword(), fullUser.getPassword());

    if (!equals) throw new IllegalArgumentException("The provided password is not valid");

    fullUser.setEnabled(false);

    accountService.save(fullUser);

    return new DisableAccountOutput();
  }

}
