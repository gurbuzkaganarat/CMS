package com.gka.service;

import java.util.List;

import com.gka.model.entity.User;
import com.gka.model.request.RoleUpdateRequest;
import com.gka.model.response.UserResponse;

public interface IUserService {

	public UserResponse getUserById(Long userId);
	
	public UserResponse updateUserRole(Long userId , RoleUpdateRequest request);
	
	public User findUserById(Long userId);
	
	public List<UserResponse> gelAllUsers();
	
	 public void deleteUserById(Long userId);
	 
	 
}
