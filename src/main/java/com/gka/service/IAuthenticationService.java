package com.gka.service;

import com.gka.model.request.LoginRequest;
import com.gka.model.request.RefreshTokenRequest;
import com.gka.model.request.RegisterRequest;
import com.gka.model.response.AuthResponse;
import com.gka.model.response.UserResponse;

public interface IAuthenticationService {
	
	public UserResponse register(RegisterRequest input);

	public AuthResponse  authenticate(LoginRequest input );
	
	public AuthResponse refreshToken(RefreshTokenRequest input);
}
