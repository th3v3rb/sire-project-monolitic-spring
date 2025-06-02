package com.dantesoft.siremono.modules.auth._rest;

import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.modules.auth.authentication.actions.*;
import com.dantesoft.siremono.modules.auth.authentication.store.dto.AllowedAccessDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
  private final CommandExecutor handler;

  @PostMapping("/register")
  ResponseEntity<Void> register(
      @Valid @RequestBody RegisterAccountInput input) {
    handler.execute(RegisterAccountAction.class, input);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/login")
  public ResponseEntity<AllowedAccessDTO> login(
      @Valid @RequestBody LoginUserInput input
  ) {
    LoginUserOutput output = handler.execute(LoginUserAction.class, input);

    var refreshToken = ResponseCookie.from("refreshToken", output.getRefreshToken().getToken())
        .httpOnly(true)
        .secure(false)
        .path("/")
        .maxAge(Duration.ofDays(7))
        .sameSite("Lax")
        .build();

    var body = new AllowedAccessDTO();
    body.setJwt(output.getJwt());

    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, refreshToken.toString())
        .body(body);
  }

  @PostMapping("/refresh")
  public ResponseEntity<AllowedAccessDTO> refreshToken(
      @CookieValue(name = "refreshToken", required = false)
      String refreshToken
  ) {

    if (refreshToken == null) {
      log.info("No refresh token provided, refresh access not allowed");
      return ResponseEntity
          .status(HttpStatus.UNAUTHORIZED)
          .build();
    }

    var input = new RefreshAccessTokenInput();
    input.setRefreshToken(refreshToken);
    var out = handler.execute(RefreshAccessTokenAction.class, input);

    var body = new AllowedAccessDTO();
    body.setJwt(out.getJwt());

    return ResponseEntity.ok().body(body);
  }


  @PostMapping("/logout")
  public ResponseEntity<Void> logout() {
    var deleteRefreshTokenCookie = ResponseCookie
        .from("refreshToken", "")
        .httpOnly(true)
        .secure(false)
        .path("/api/v1/auth/refresh")
        .maxAge(0)
        .build();

    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, deleteRefreshTokenCookie.toString())
        .build();
  }

  @PostMapping("/verification-email")
  public ResponseEntity<SendVerificationEmailOutput> sendAccountVerificationMail(
      @Valid @RequestBody SendVerificationEmailInput input
  ) {
    handler.executeAsync(SendVerificationEmailAction.class, input);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/verify-email")
  public ResponseEntity<VerifyAccountOutput> verifyEmail(
      @RequestParam UUID token
  ) {
    VerifyAccountInput input = new VerifyAccountInput();
    input.setToken(token.toString());
    var output = handler.execute(VerifyAccountAction.class, input);
    return ResponseEntity.ok(output);
  }

  @PostMapping("/forgot-password")
  public ResponseEntity<SendResetPasswordEmailOutput> sendResetPasswordEmail(
      @Valid @RequestBody SendResetPasswordEmailInput input
  ) {
    handler.executeAsync(SendResetPasswordEmailAction.class, input);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/reset-password/{token}")
  public ResponseEntity<ResetPasswordOutput> resetPassword(
      @PathVariable String token,
      @Valid @RequestBody ResetPasswordInput input
  ) {
    input.setToken(token);
    var out = handler.execute(ResetPasswordAction.class, input);
    return ResponseEntity.ok(out);
  }

  @PatchMapping("/change-password")
  public ResponseEntity<Void> changePassword(
      @RequestBody ChangePasswordInput input
  ) {
    handler.execute(ChangePasswordAction.class, input);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/disable")
  public ResponseEntity<Void> disable(
      @RequestBody DisableAccountInput input
  ) {
    handler.execute(DisableAccountAction.class, input);
    return ResponseEntity.ok().build();
  }

}
