package com.gka.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gka.controller.IRestAuthenticationController;
import com.gka.controller.RestBaseController;
import com.gka.controller.RootEntity;
import com.gka.model.request.LoginRequest;
import com.gka.model.request.RefreshTokenRequest;
import com.gka.model.request.RegisterRequest;
import com.gka.model.response.AuthResponse;
import com.gka.model.response.UserResponse;
import com.gka.service.IAuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {

	@Autowired
	private IAuthenticationService authenticationService;
	
	@PostMapping("/register")
	@Override
	public RootEntity<UserResponse> register(@Valid @RequestBody RegisterRequest input) {
		
		return ok(authenticationService.register(input));
	}

	@PostMapping("/authenticate")
	@Override
	public RootEntity<AuthResponse> authenticate( @Valid @RequestBody LoginRequest input) {
		return ok(authenticationService.authenticate(input));
	}
	
	@PostMapping("/refreshToken")
	@Override
	public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest input) {
		// TODO Auto-generated method stub
		return ok(authenticationService.refreshToken(input)); 
	}

	

}
