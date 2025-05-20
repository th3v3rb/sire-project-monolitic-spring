package com.dantesoft.siremono.modules.auth.actions;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.exception.ValidationException;
import com.dantesoft.siremono.modules.auth.store.AccountService;
import com.dantesoft.siremono.modules.auth.store.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Slf4j
@RequiredArgsConstructor
public class LoginUserAction extends AbstractCommand<LoginUserInput, LoginUserOutput> {

	private final AuthenticationManager authManager;
	private final AccountService authService;
	private final JwtService jwtService;

	@Override
	public LoginUserOutput doExecute() {
		var email = getInput().getEmail();
		var password = getInput().getPassword();
		var authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
		authManager.authenticate(authenticationToken);

		var user = authService.findByEmailOrFail(email);

		if (!user.isCredentialsNonExpired()) {
			throw new ValidationException("The credentials has been expired");
		}

		if (!user.isAccountNonExpired()) {
			throw new ValidationException("The account has been expired");
		}

		if (!user.isEnabled()) {
			throw new ValidationException("The account has been banned");
		}

		String jwt = jwtService.generateToken(user);

		LoginUserOutput output = new LoginUserOutput();
		output.setToken(jwt);

		return output;
	}
}