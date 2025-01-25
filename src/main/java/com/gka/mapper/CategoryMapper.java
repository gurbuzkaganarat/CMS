package com.gka.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.gka.model.entity.Category;
import com.gka.model.response.CategoryResponse;

public class CategoryMapper {

  
	public static CategoryResponse toResponse(Category  category, boolean includeSubCategories) {
		
		Set<CategoryResponse> subCategories = new HashSet<>();
		boolean hasSubCategories=false;
		
		
		if (includeSubCategories ) {
			
			subCategories = category.getSuCategories().stream()
					.map(subCategory -> toResponse(subCategory, false))
					.collect(Collectors.toSet());
			hasSubCategories = !subCategories.isEmpty();
		}
		else {
			hasSubCategories = !category.getSuCategories().isEmpty();
		}
		
		Long parentId = (category.getParent() != null) ? category.getParent().getId() : null;
		
		return CategoryResponse.builder()
				.id(category.getId())
				.name(category.getName())
				.slug(category.getSlug())
				.description(category.getDescription())
				.parentId(parentId)
				.hasSubCategories(hasSubCategories)
				.subCategories(subCategories)
				.createdDateTime(category.getCreatedDateTime())
				.modifiedDateTime(category.getModifiedDateTime())
				.build();
		
	}
	
    
}
