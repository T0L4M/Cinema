package com.eproject.Cinema.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eproject.Cinema.entities.User;
import com.eproject.Cinema.repositories.AccountRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AuthorUserDetailService implements UserDetailsService {

	@Autowired
	AccountRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(userName);
		if (user != null) {
			List<GrantedAuthority> authorities = new ArrayList<>();
			if (user.getRole().equals("ADMIN")) {
				authorities.add(new SimpleGrantedAuthority("ADMIN"));
			}
			authorities.add(new SimpleGrantedAuthority("USER"));

			return new org.springframework.security.core.userdetails.User(
					user.getUserName(),
					user.getPassword(),
					authorities);
		} else {
			throw new UsernameNotFoundException("Invalid email or password.");
		}
	}
}