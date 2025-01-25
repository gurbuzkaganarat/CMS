package com.gka.model.response;

import java.util.Set;

import com.gka.model.dto.BaseDto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class CategoryResponse extends BaseDto {
	
	
	private String name;
	
	private String slug;
	
	private String description;
	
	private Long parentId;
	
	private boolean hasSubCategories;
	
	private Set<CategoryResponse> subCategories;
	
	
	
	

}
