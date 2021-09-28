package com.app.security.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dao.CredentialRepository;
import com.app.pojos.Credential;
import com.app.pojos.Role;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private CredentialRepository credRepo;

	@Autowired
	private PasswordEncoder pEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Credential user = credRepo.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("Invalid Username"));
		List<? extends GrantedAuthority> roles = null;
		if (user.getRole() == Role.ADMIN)
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + Role.ADMIN));
		else
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + Role.STUDENT));
		return new User(user.getUserName(), pEncoder.encode(user.getPassword()), roles);
	}

}
