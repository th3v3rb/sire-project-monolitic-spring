package com.dantesoft.siremono.modules.auth.rest;

import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.auth.actions.*;
import com.dantesoft.siremono.modules.auth.store.AuthCookieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
