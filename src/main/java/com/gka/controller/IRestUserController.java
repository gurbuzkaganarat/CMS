package com.gka.controller;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.gka.model.request.RoleUpdateRequest;
import com.gka.model.response.UserResponse;

public interface IRestUserController {
	
	public RootEntity<UserResponse> getUserById(Long userId);
	
	public RootEntity<UserResponse> updateUserRole(Long userId , RoleUpdateRequest request);	
	
	public RootEntity<List<UserResponse>> gelAllUsers();
	
	public void deleteUserById(Long userId,Authentication authentication);

}
