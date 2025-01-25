package com.gka.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	private String password;

}
