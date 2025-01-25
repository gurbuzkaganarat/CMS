package com.gka.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {
	
	@NotEmpty(message = "Category name cannot be empty")
	@Size(min = 3,max = 100,message = "Category name must be between 3 and 100 characters")
	private String name;
	
	@Size(max=255,message = "Description cannot exceed 255 characters")
	private String description;
	
	private Long parentId;

}
