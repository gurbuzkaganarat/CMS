package com.gka.mapper;

import com.gka.model.entity.Tag;
import com.gka.model.response.TagResponse;

public class TagMapper {
	
	public static TagResponse toResponse(Tag tag ) {
		
		return TagResponse.builder()
				.id(tag.getId())
				.createdDateTime(tag.getCreatedDateTime())
				.modifiedDateTime(tag.getModifiedDateTime())
				.name(tag.getName())
				.slug(tag.getSlug())
				.build();
		
	}

}
