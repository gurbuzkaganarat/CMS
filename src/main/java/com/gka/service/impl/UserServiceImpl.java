package com.gka.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gka.exception.BaseException;
import com.gka.exception.ErrorMessage;
import com.gka.exception.MessageType;
import com.gka.mapper.UserMapper;
import com.gka.model.entity.Role;
import com.gka.model.entity.User;
import com.gka.model.request.RoleUpdateRequest;
import com.gka.model.response.UserResponse;
import com.gka.repository.UserRepository;
import com.gka.service.IUserService;

@Service

public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserRepository userRepository;

	
	
	
	
	@Override
	public User findUserById(Long userId) {
		return userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NOT_FOUND, "User not found" )));
	}

	
	@Override
	public UserResponse getUserById(Long userId) {
		
			User user = findUserById(userId);
			return UserMapper.toResponse(user);
	}
	

	@Override
	public List<UserResponse> gelAllUsers() {
		
		List<User> userList = userRepository.findAll();
		
	 return	userList.stream()
		.map(UserMapper::toResponse)
		.collect(Collectors.toList());
		
	}


	@Override
	public UserResponse updateUserRole(Long userId, RoleUpdateRequest request) {
		
		User user = findUserById(userId);
		
		if (user.getRole() == Role.ADMIN) {
			throw new BaseException(new ErrorMessage(MessageType.FORBIDDEN, "Admin's role cannot be changed by another admin"));
		}
		
		Role newrole;
		try {
			newrole = Role.valueOf(request.getRole().toUpperCase());
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.NOT_FOUND, "Invalid role"+ request.getRole()));
		}
		
		
		user.setRole(newrole);
		userRepository.save(user);
		return UserMapper.toResponse(user);
		
		
	}
	
	
	
	@Override
	public void deleteUserById(Long userId) {
		
		User user = findUserById(userId);
		user.setEmail("deleted_user");
		userRepository.save(user);
		
	}



	



	


	




	

	

}
