package com.gka.model.response;

import com.gka.model.dto.BaseDto;
import com.gka.model.entity.Role;
import com.gka.model.entity.Status;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
public class UserResponse  extends BaseDto{
	
	  private String username;
	    private String email;
	    private Role role;      
	    private Status status;   
	
	

}
