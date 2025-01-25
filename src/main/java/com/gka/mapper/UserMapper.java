package com.gka.mapper;

import com.gka.model.entity.User;
import com.gka.model.response.UserResponse;

public class UserMapper {
	
	public static UserResponse toResponse(User user) {
	    if (user == null) {
	        return null;
	    }

	    return UserResponse.builder()
	    		.id(user.getId())
	    		.email(user.getEmail())
	    		.username(user.getUsername())
	    		.role(user.getRole())
	    		.status(user.getStatus())
	    		.createdDateTime(user.getCreatedDateTime())
	    		.modifiedDateTime(user.getModifiedDateTime())
	    		.build();
	            

	}
	
	
	
	
}




