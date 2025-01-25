package com.gka.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gka.controller.IRestUserController;
import com.gka.controller.RestBaseController;
import com.gka.controller.RootEntity;
import com.gka.exception.BaseException;
import com.gka.exception.ErrorMessage;
import com.gka.exception.MessageType;
import com.gka.jwt.AuthenticationFacade;
import com.gka.model.entity.User;
import com.gka.model.request.RoleUpdateRequest;
import com.gka.model.response.UserResponse;
import com.gka.service.IUserService;

@RestController
@RequestMapping("/api/v1/users")
public class RestUserControllerImpl extends RestBaseController implements IRestUserController {

	@Autowired
	IUserService userService;
	
	@Autowired
	AuthenticationFacade authenticationFacade;
	
	@GetMapping("/{userId}")
	@Override
	public RootEntity<UserResponse> getUserById(  @PathVariable(value="userId") Long userId) {
		
		 try {
		        
		        return ok(userService.getUserById(userId));
		    } catch (BaseException ex) {
		        
		        return error("User not found with ID: " + userId);
		    }
	}
	
	
	@GetMapping("/list")
	@Override
	public RootEntity<List<UserResponse>> gelAllUsers() {
		return ok(userService.gelAllUsers());
	}

	@DeleteMapping("/{userId}")
	@Override
	public void deleteUserById( @PathVariable(value="userId") Long userId,Authentication authentication) {
		
		User  loggedInUser =  authenticationFacade.getUserThroughAuthentication(authentication);
		if (!loggedInUser.getId().equals(userId)) {
            throw new BaseException(new ErrorMessage(MessageType.FORBIDDEN,"You are not allowed to delete this user")); 
        }
		
		userService.deleteUserById(userId);
		
	}

 	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{userId}/role")
	@Override
	public RootEntity<UserResponse> updateUserRole(@PathVariable(value="userId") Long userId, @RequestBody  RoleUpdateRequest request) {
		return ok(userService.updateUserRole(userId, request));
	}






}
