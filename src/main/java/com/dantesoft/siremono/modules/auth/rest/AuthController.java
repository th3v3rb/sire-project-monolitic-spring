package com.dantesoft.siremono.modules.auth.rest;

import java.util.UUID;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.auth.actions.ChangePasswordAction;
import com.dantesoft.siremono.modules.auth.actions.ChangePasswordInput;
import com.dantesoft.siremono.modules.auth.actions.DisableAccountAction;
import com.dantesoft.siremono.modules.auth.actions.DisableAccountInput;
import com.dantesoft.siremono.modules.auth.actions.LoginUserAction;
import com.dantesoft.siremono.modules.auth.actions.LoginUserInput;
import com.dantesoft.siremono.modules.auth.actions.LoginUserOutput;
import com.dantesoft.siremono.modules.auth.actions.RegisterAccountAction;
import com.dantesoft.siremono.modules.auth.actions.RegisterAccountInput;
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
import com.dantesoft.siremono.modules.auth.store.AuthCookieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
  private final CommandExecutor handler;
  private final AuthCookieService cookieService;

  @PostMapping("/register")
  ResponseEntity<Void> register(@Valid
  @RequestBody RegisterAccountInput input) {
    handler.execute(RegisterAccountAction.class, input);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/login")
  public ResponseEntity<Void> login(@Valid
  @RequestBody LoginUserInput input, @RequestParam(
    defaultValue = "false") boolean rememberMe) {

    LoginUserOutput output = handler.execute(LoginUserAction.class, input);
    var cookie = cookieService.createTokenCookie(output.getToken(), rememberMe);

    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
  }

  @PostMapping("/logout")
  public ResponseEntity<Void> logout() {
    var deleteCookie = cookieService.clearTokenCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, deleteCookie.toString()).build();
  }

  @PostMapping("/verification-email")
  public ResponseEntity<SendVerificationEmailOutput> sendAccountVerificationMail(@Valid
  @RequestBody SendVerificationEmailInput input) {
    handler.executeAsync(SendVerificationEmailAction.class, input);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/verify-email")
  public ResponseEntity<VerifyAccountOutput> verifyEmail(@RequestParam UUID token) {
    VerifyAccountInput input = new VerifyAccountInput();
    input.setToken(token.toString());
    var output = handler.execute(VerifyAccountAction.class, input);
    return ResponseEntity.ok(output);
  }

  @PostMapping("/forgot-password")
  public ResponseEntity<SendResetPasswordEmailOutput> sendResetPasswordEmail(@Valid
  @RequestBody SendResetPasswordEmailInput input) {
    handler.executeAsync(SendResetPasswordEmailAction.class, input);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/reset-password/{token}")
  public ResponseEntity<ResetPasswordOutput> resetPassword(@PathVariable String token, @Valid
  @RequestBody ResetPasswordInput input) {
    input.setToken(token);
    var out = handler.execute(ResetPasswordAction.class, input);
    return ResponseEntity.ok(out);
  }

  @PatchMapping("/change-password")
  public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordInput input) {
    handler.execute(ChangePasswordAction.class, input);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/disable")
  public ResponseEntity<Void> disable(@RequestBody DisableAccountInput input) {
    handler.execute(DisableAccountAction.class, input);
    return ResponseEntity.ok().build();
  }


}
