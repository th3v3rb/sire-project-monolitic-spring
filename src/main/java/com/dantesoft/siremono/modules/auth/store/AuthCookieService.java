package com.dantesoft.siremono.modules.auth.store;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class AuthCookieService {
	@Value("${internal.jwt.expiration}")
	private long expirationTime;

	private final String jwtCookieName = "jwt";
	private final boolean httpOnly = true;
	private final boolean secure = false;
	private final String path = "/";
	private final String sameSite = "Lax";

	public ResponseCookie createTokenCookie(
			String token,
			boolean rememberMe) {
		Assert.hasText(token, "El token no puede ser nulo o vacío");

		ResponseCookie.ResponseCookieBuilder builder = ResponseCookie
				.from(jwtCookieName, token)
				.httpOnly(httpOnly)
				.secure(secure)
				.path(path)
				.sameSite(sameSite);

		if (rememberMe) {
			builder.maxAge(expirationTime);
		}

		return builder.build();
	}

	public ResponseCookie clearTokenCookie() {
		return ResponseCookie
				.from(jwtCookieName, "")
				.httpOnly(httpOnly)
				.secure(secure)
				.path(path)
				.maxAge(0)
				.sameSite(sameSite)
				.build();
	}
}