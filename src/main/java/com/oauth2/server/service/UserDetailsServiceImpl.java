package com.oauth2.server.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oauth2.server.entity.User;
import com.oauth2.server.repository.UserRepository;
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findUserByEmail(username);
		
		if(user==null)
			throw new UsernameNotFoundException("Email not found exception..");
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getEnabled(), true, true, true, getAuthorities(user.getRole()));
	}
	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
		List<GrantedAuthority> authority=new ArrayList<>();
		authority.add(new SimpleGrantedAuthority(role));
		return authority;
	}
}
