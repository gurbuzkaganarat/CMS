package com.gka.model.request;

import java.util.Map;
import java.util.Set;

import com.gka.model.entity.ContentType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ContentRequest {
	
	 @NotEmpty(message = "Title is required")
	    private String title;


	 @NotEmpty(message = "Content is required")
	    private String content;

	 @NotNull(message = "Content type is required")
	    private ContentType contentType;

	 
	    private Map<String, Object> metadata;

	    @NotEmpty(message = "Category IDs cannot be empty.")
	    private Set<Long> categoryIds; 
	    
	    private Set<Long> tagIds; 

}
