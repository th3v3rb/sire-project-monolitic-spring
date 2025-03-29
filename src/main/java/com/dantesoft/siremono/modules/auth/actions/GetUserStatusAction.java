package com.dantesoft.siremono.modules.auth.actions;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.dantesoft.siremono.internal.actions.AbstractAction;
import com.dantesoft.siremono.internal.dto.UserDto;
import com.dantesoft.siremono.modules.auth.store.services.AuthService;
import com.dantesoft.siremono.modules.auth.store.services.JwtService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetUserStatusAction extends AbstractAction<GetUserStatusInput, GetUserStatusOutput> {
  private final JwtService tokenService;
  private final AuthService authService;

  @Override
  public GetUserStatusOutput doExecute() {
    log.info(
        "Started [GetUserStatusAction] process for user with input {}",
        getInput().toString());
    var isTokenValid = tokenService.isTokenValid(getInput().getJwt());

    if (!isTokenValid) {
      log.warn("Token not valid on [validate token] process");
      throw new ResponseStatusException(
          HttpStatus.UNAUTHORIZED, "Token not valid");
    }

    var userId = tokenService.extractUserId(getInput().getJwt());
    var user = this.authService.findById(userId).orElseThrow(() -> {
      log.warn("Not found user with id {}", userId);
      return new ResponseStatusException(
          org.springframework.http.HttpStatus.NOT_FOUND, "User not found");
    });

    var userDto = UserDto.builder().email(user.getEmail())
        .username(user.getUsername()).roles(user.getRoles())
        .emailVerifiedAt(user.getEmailVerifiedAt())
        .organization(user.getOrganization()).build();

    var output = new GetUserStatusOutput();
    output.setUser(userDto);

    log.info(
        "Success [GetUserStatusAction] process for user with input {} and result {}",
        getInput(), output);
    return output;
  }

}
