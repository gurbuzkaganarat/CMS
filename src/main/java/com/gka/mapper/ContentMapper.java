package com.gka.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.gka.model.entity.Content;
import com.gka.model.entity.ContentVersion;
import com.gka.model.response.CategoryResponse;
import com.gka.model.response.ContentResponse;
import com.gka.model.response.TagResponse;
import com.gka.model.response.UserResponse;

public class ContentMapper {
	
	public static ContentResponse toResponse(Content content) {
		
		UserResponse createdByResponse = content.getCreatedBy() != null 
			? UserMapper.toResponse(content.getCreatedBy())
						:null;
	
		UserResponse updatedByResponse = content.getUpdatedBy() != null
			? UserMapper.toResponse(content.getUpdatedBy())
					: null;
		
		Set<CategoryResponse> categoryResponses = content.getCategories().stream()
				.map(category -> CategoryMapper.toResponse(category, false))
				.collect(Collectors.toSet());
		
		 Set<TagResponse> tagResponses = content.getTags().stream()
		            .map(TagMapper::toResponse) 
		            .collect(Collectors.toSet());
		 
		 return ContentResponse.builder()
	                .id(content.getId())
	                .createdDateTime(content.getCreatedDateTime())
	                .modifiedDateTime(content.getModifiedDateTime())
	                .title(content.getTitle())
	                .slug(content.getSlug())
	                .content(content.getContent())
	                .contentType(content.getContentType())
	                .contentStatus(content.getContentStatus())
	                .publishDate(content.getPublishDate())	                
	                .metadata(content.getMetadata())
	                .createdBy(createdByResponse)
	                .updatedBy(updatedByResponse)
	                .categories(categoryResponses)
	                .tags(tagResponses)
	                .build();
		
		
		
	}
	
	public static ContentResponse toResponse(ContentVersion contentVersion) {
        UserResponse createdByResponse = contentVersion.getVersionCreatedBy() != null
                ? UserMapper.toResponse(contentVersion.getVersionCreatedBy())
                : null;

        UserResponse updatedByResponse = contentVersion.getVersionUpdatedBy() != null
                ? UserMapper.toResponse(contentVersion.getVersionUpdatedBy())
                : null;

        Set<CategoryResponse> categoryResponses = contentVersion.getCategories().stream()
                .map(category -> CategoryMapper.toResponse(category, false))
                .collect(Collectors.toSet());

        Set<TagResponse> tagResponses = contentVersion.getTags().stream()
                .map(TagMapper::toResponse)
                .collect(Collectors.toSet());

        return ContentResponse.builder()
                .id(contentVersion.getId()) 
                .originalContentId(contentVersion.getOriginalContentId())
                .title(contentVersion.getTitle())
                .slug(contentVersion.getSlug())
                .content(contentVersion.getContent())
                .contentType(contentVersion.getContentType())
                .contentStatus(contentVersion.getContentStatus())
                .metadata(contentVersion.getMetadata())
                .createdBy(createdByResponse)
                .updatedBy(updatedByResponse)
                .categories(categoryResponses)
                .tags(tagResponses)
                .version(contentVersion.getVersionNumber()) 
                .build();
    }
}

