package com.gka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gka.jwt.UserDetailsImpl;
import com.gka.model.entity.User;
import com.gka.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		 User user = userRepository.findByEmail(email)
		            .orElseThrow(() -> new UsernameNotFoundException(
		                "User not found with email: " + email));

		        return new UserDetailsImpl(user);
	}
	
	

}
