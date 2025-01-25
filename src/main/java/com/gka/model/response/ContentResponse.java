package com.gka.model.response;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;

import com.gka.model.dto.BaseDto;
import com.gka.model.entity.ContentStatus;
import com.gka.model.entity.ContentType;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ContentResponse extends BaseDto{
	
	private Long originalContentId;
	private String title; 
    private String slug; 
    private String content; 
    private ContentType contentType; 
    private ContentStatus contentStatus; 
    private ZonedDateTime publishDate; 
    private Long version;
    private Map<String, Object> metadata; 
    
    private UserResponse createdBy;
    private UserResponse updatedBy;
    private Set<CategoryResponse> categories; 
    private Set<TagResponse> tags; 

}
