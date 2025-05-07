package com.dantesoft.siremono.modules.auth.actions;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.dantesoft.siremono.internal.commands.AbstractCommand;
import com.dantesoft.siremono.internal.commands.CommandExecutor;
import com.dantesoft.siremono.internal.events.DomainEventPublisher;
import com.dantesoft.siremono.modules.auth.AuthErrors.ValidationException;
import com.dantesoft.siremono.modules.auth.events.UserRegisteredEvent;
import com.dantesoft.siremono.modules.auth.store.RoleService;
import com.dantesoft.siremono.modules.auth.store.AccountService;
import com.dantesoft.siremono.modules.auth.store.entity.AccountEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterAccountAction extends AbstractCommand<RegisterAccountInput, RegisterAccountOutput> {
	private final CommandExecutor handler;
	private final PasswordEncoder passwordEncoder;
	private final AccountService authService;
	private final RoleService roleService;
	private final DomainEventPublisher domainEventPublisher;

	@Override
	public RegisterAccountOutput doExecute() {
		if (!getInput().passwordsMatch()) {
			throw new ValidationException("The password doenst match");
		}

		if (authService.findByEmail(getInput().getEmail()).isPresent()) {
			throw new ValidationException("The email already has been taken");
		}

		var user = new AccountEntity();
		user.setUsername(getInput().getUsername());
		user.setPassword(passwordEncoder.encode(getInput().getPassword()));
		user.setRoles(Set.of(roleService.getRoleByName("GUEST")));
		user.setEmail(getInput().getEmail());
		user.setEnabled(true);
		authService.save(user);

		handleSendEmailVerification();

		domainEventPublisher.publish(new UserRegisteredEvent(user));

		return null;
	}

	private void handleSendEmailVerification() {
		var input = new SendVerificationEmailInput();
		input.setEmail(getInput().getEmail());

		handler.executeAsync(SendVerificationEmailAction.class, input);
	}
}
