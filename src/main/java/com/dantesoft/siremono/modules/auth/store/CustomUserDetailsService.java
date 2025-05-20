package com.dantesoft.siremono.modules.auth.store;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final AccountRepository userRepository;

	@Override
	@Transactional(
			readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
	}
}
