package com.oauth2.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oauth2.server.entity.User;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username=authentication.getName();
		String password=authentication.getCredentials().toString();
		UserDetails user=userDetailsServiceImpl.loadUserByUsername(username);
		
		
		return checkPassword(user,password);
	}


	private Authentication checkPassword(UserDetails user, String password) {

		if(passwordEncoder.matches(password, user.getPassword()))
			return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),user.getAuthorities());
		else
			throw new BadCredentialsException("Bad Credentials!!!!");
		
	}


	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
