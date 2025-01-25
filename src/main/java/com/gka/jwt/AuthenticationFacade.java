package com.gka.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.gka.model.entity.User;
import com.gka.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationFacade {
	
	@Autowired
	private  UserRepository userRepository;
	
	public User getUserThroughAuthentication(Authentication auth) {
		
		 UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
		 return userRepository.findByEmail(user.getUsername())
				 .orElseThrow(() -> new RuntimeException("user not found"));
	}
	

}
