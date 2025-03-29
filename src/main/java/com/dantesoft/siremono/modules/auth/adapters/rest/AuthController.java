package com.dantesoft.siremono.modules.auth.adapters.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dantesoft.siremono.internal.actions.io.AbstractRest;
import com.dantesoft.siremono.modules.auth.actions.GetUserStatusAction;
import com.dantesoft.siremono.modules.auth.actions.GetUserStatusInput;
import com.dantesoft.siremono.modules.auth.actions.GetUserStatusOutput;
import com.dantesoft.siremono.modules.auth.actions.LoginUserAction;
import com.dantesoft.siremono.modules.auth.actions.LoginUserInput;
import com.dantesoft.siremono.modules.auth.actions.LoginUserOutput;
import com.dantesoft.siremono.modules.auth.actions.RegisterAccountAction;
import com.dantesoft.siremono.modules.auth.actions.RegisterAccountInput;
import com.dantesoft.siremono.modules.auth.actions.RegisterAccountOutput;
import com.dantesoft.siremono.modules.auth.actions.ResetPasswordAction;
import com.dantesoft.siremono.modules.auth.actions.ResetPasswordInput;
import com.dantesoft.siremono.modules.auth.actions.ResetPasswordOutput;
import com.dantesoft.siremono.modules.auth.actions.SendResetPasswordEmailAction;
import com.dantesoft.siremono.modules.auth.actions.SendResetPasswordEmailInput;
import com.dantesoft.siremono.modules.auth.actions.SendResetPasswordEmailOutput;
import com.dantesoft.siremono.modules.auth.actions.SendVerificationEmailAction;
import com.dantesoft.siremono.modules.auth.actions.SendVerificationEmailInput;
import com.dantesoft.siremono.modules.auth.actions.SendVerificationEmailOutput;
import com.dantesoft.siremono.modules.auth.actions.VerifyAccountAction;
import com.dantesoft.siremono.modules.auth.actions.VerifyAccountInput;
import com.dantesoft.siremono.modules.auth.actions.VerifyAccountOutput;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController extends AbstractRest {

  @PostMapping("/register")
  ResponseEntity<RegisterAccountOutput> register(
      @Valid @RequestBody RegisterAccountInput input) {
    return handle(RegisterAccountAction.class, input);
  }

  @PostMapping("/login")
  ResponseEntity<LoginUserOutput> login(
      @Valid @RequestBody LoginUserInput input) {
    return handle(LoginUserAction.class, input);
  }

  @PostMapping("/verification-email")
  public ResponseEntity<SendVerificationEmailOutput> sendAccountVerificationMail(
      @Valid @RequestBody SendVerificationEmailInput input) {
    return handle(SendVerificationEmailAction.class, input);
  }

  @PatchMapping("/verify-email/{token}")
  public ResponseEntity<VerifyAccountOutput> verifyEmail(
      @Valid
      @PathVariable
      @NotBlank(message = "El token es requerido") String token) {
    var input = new VerifyAccountInput();
    input.setToken(token);
    return handle(VerifyAccountAction.class, input);
  }

  @PostMapping("/forgot-password")
  public ResponseEntity<SendResetPasswordEmailOutput> sendResetPasswordEmail(
      @Valid @RequestBody SendResetPasswordEmailInput input) {
    return handle(SendResetPasswordEmailAction.class, input);
  }

  @GetMapping("/status/{token}")
  public ResponseEntity<GetUserStatusOutput> validateToken(
      @PathVariable String token,
      GetUserStatusInput input) {
    input.setJwt(token);
    return handle(GetUserStatusAction.class, input);
  }

  @PatchMapping("/reset-password/{token}")
  public ResponseEntity<ResetPasswordOutput> resetPassword(
      @PathVariable String token,
      @Valid @RequestBody ResetPasswordInput input) {
    input.setToken(token);
    return handle(ResetPasswordAction.class, input);
  }

}
