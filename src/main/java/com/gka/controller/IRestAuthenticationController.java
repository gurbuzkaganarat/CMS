package com.gka.controller;

import com.gka.model.request.LoginRequest;
import com.gka.model.request.RefreshTokenRequest;
import com.gka.model.request.RegisterRequest;
import com.gka.model.response.AuthResponse;
import com.gka.model.response.UserResponse;

public interface IRestAuthenticationController {
	
	public RootEntity<UserResponse> register (RegisterRequest input);
	
	public RootEntity<AuthResponse> authenticate(LoginRequest input);
	
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);

}
