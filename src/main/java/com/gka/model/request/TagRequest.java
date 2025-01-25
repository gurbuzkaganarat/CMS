package com.gka.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagRequest {
	
	@NotEmpty(message ="tag name cannot be empty" )
	private String name;

}
