package com.dantesoft.siremono.modules.auth.actions;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.internal.dto.UserDto;
import com.dantesoft.siremono.modules.auth.AuthErrors;
import com.dantesoft.siremono.modules.auth.AuthErrors.ValidationException;
import com.dantesoft.siremono.modules.auth.store.services.AuthService;
import com.dantesoft.siremono.modules.auth.store.services.JwtService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginUserAction extends AbstractAction<LoginUserInput, LoginUserOutput> {

  private final AuthenticationManager authManager;
  private final AuthService authService;
  private final JwtService jwtService;

  @Override
  public LoginUserOutput doExecute() {
    var email = getInput().getEmail();
    var password = getInput().getPassword();
    var authenticationToken = new UsernamePasswordAuthenticationToken(
        email, password);
    authManager.authenticate(authenticationToken);

    var user = authService.findByEmail(email)
        .orElseThrow(() -> new AuthErrors.NotFoundException("User not found"));

    if (!user.isCredentialsNonExpired()) {
      throw new ValidationException(
          "Las credenciales del usuario han expirado");
    }

    if (!user.isAccountNonExpired()) {
      throw new ValidationException("La cuenta ha expirado");
    }

    if (!user.isEnabled()) {
      throw new ValidationException("La cuenta ha sido deshabilitada");
    }

    String jwt = jwtService.generateToken(user);
    var userDto = UserDto.builder().id(user.getId())
        .username(user.getUsername()).email(user.getEmail())
        .emailVerifiedAt(user.getEmailVerifiedAt()).roles(user.getRoles())
        .organization(user.getOrganization()).build();

    String message = (userDto.getEmailVerifiedAt() == null)
        ? "MUST_VERIFY_ACCOUNT_FIRST"
        : "Ha iniciado sesión correctamente";

    LoginUserOutput output = new LoginUserOutput();
    output.setMessage(message);
    output.setToken(jwt);
    output.setUser(userDto);

    return output;
  }
}