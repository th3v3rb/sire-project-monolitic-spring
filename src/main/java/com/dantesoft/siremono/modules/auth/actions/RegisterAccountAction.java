package com.dantesoft.siremono.modules.auth.actions;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.modules.auth.AuthErrors.ValidationException;
import com.dantesoft.siremono.modules.auth.store.entity.User;
import com.dantesoft.siremono.modules.auth.store.services.AuthService;
import com.dantesoft.siremono.modules.auth.store.services.JwtService;
import com.dantesoft.siremono.modules.auth.store.services.RoleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisterAccountAction extends AbstractAction<RegisterAccountInput, RegisterAccountOutput> {

  private final PasswordEncoder passwordEncoder;
  private final AuthService authService;
  private final RoleService roleService;
  private final JwtService tokenService;

  @Override
  public RegisterAccountOutput doExecute() {
    if (!getInput().passwordsMatch()) {
      throw new ValidationException("The password doenst match");
    }

    if (authService.findByEmail(getInput().getEmail()).isPresent()) {
      throw new ValidationException("The email already has been taken");
    }

    var user = new User();
    user.setUsername(getInput().getUsername());
    user.setPassword(passwordEncoder.encode(getInput().getPassword()));
    user.setRoles(Set.of(roleService.getRoleByName("GUEST")));
    user.setEmail(getInput().getEmail());
    user.setEnabled(true);
    var savedUser = authService.save(user);

    var out = new RegisterAccountOutput();
    out.setToken(this.tokenService.generateToken(savedUser));
    return out;
  }
}
