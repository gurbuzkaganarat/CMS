package com.gka.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gka.exception.BaseException;
import com.gka.exception.ErrorMessage;
import com.gka.exception.MessageType;
import com.gka.jwt.JwtService;
import com.gka.mapper.UserMapper;
import com.gka.model.entity.RefreshToken;
import com.gka.model.entity.Role;
import com.gka.model.entity.Status;
import com.gka.model.entity.User;
import com.gka.model.request.LoginRequest;
import com.gka.model.request.RefreshTokenRequest;
import com.gka.model.request.RegisterRequest;
import com.gka.model.response.AuthResponse;
import com.gka.model.response.UserResponse;
import com.gka.repository.RefreshTokenRepository;
import com.gka.repository.UserRepository;
import com.gka.service.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	
	
	
	
	private User createUser(RegisterRequest input) {
		User  newUser = new User();
		newUser.setUsername(input.getUsername());
		newUser.setEmail(input.getEmail());
		newUser.setPassword(passwordEncoder.encode(input.getPassword()));
		newUser.setRole(Role.AUTHOR);
		newUser.setStatus(Status.ACTIVE);
		
		return newUser;
	}
	
	private RefreshToken createRefreshToken(User user) {
		
		
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000*60*60*4));
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setUser(user);
		return refreshToken;
		
	}
	
	
	

	
	@Override
	public UserResponse register(RegisterRequest input) {
		
		
		Optional<User> existingUserOptional=userRepository.findByEmail(input.getEmail());
		if (existingUserOptional.isPresent()) {
			
			throw new BaseException(new ErrorMessage(MessageType.CONFLICT,"Email already have exists"));
		}
			
		User savedUser = userRepository.save(createUser(input));
		
		return UserMapper.toResponse(savedUser);
	}
	
	

	@Override
	public AuthResponse authenticate(LoginRequest input) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken =
					new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword());
			
			Authentication authentication = authenticationProvider.authenticate(authenticationToken);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			Optional<User> optUser = userRepository.findByEmail(input.getEmail());
			
			
			String accessToken = jwtService.generateToken(authentication);
			RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(optUser.get()));
			
			
		   return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());
			
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.UNAUTHORIZED, "Kullanıcı adı veya Şifre Yanlış"));
		}
		
	}
	
	
	public boolean isValidRefreshToken(Date expiredDate) {
		
		return new Date().before(expiredDate);
	}

	@Override
	public AuthResponse refreshToken(RefreshTokenRequest input) {
		Optional<RefreshToken> optRefreshToken = refreshTokenRepository.findByRefreshToken(input.getRefreshToken());
		
		if (optRefreshToken.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.UNAUTHORIZED, "Refresh token bulunamadı"));
		}
		
		if (!isValidRefreshToken(optRefreshToken.get().getExpiredDate())) {
			 RefreshToken refreshToken = optRefreshToken.get();
			refreshTokenRepository.delete(refreshToken);
			throw new BaseException(new ErrorMessage(MessageType.UNAUTHORIZED, "Refresh token'ın süresi dolmuştur"));
			
		}
		
		
		
		User user = optRefreshToken.get().getUser();
		String accessToken = jwtService.generateToken(user);
		RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(user));
		
		return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
	}
	
	

}
