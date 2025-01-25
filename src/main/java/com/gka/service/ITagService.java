package com.gka.service;

import java.util.List;

import com.gka.model.entity.Tag;
import com.gka.model.request.TagRequest;
import com.gka.model.response.TagResponse;

public interface ITagService {
	
	public TagResponse saveTag(TagRequest tagRequest);
	
	public TagResponse getTagById(Long tagId);
	
	public List<TagResponse> gelAllTags();
	
	public Tag findTagById(Long tagId);
	
	public TagResponse updateTag(Long tagId , TagRequest tagRequest);
	
	public void deleteTagById(Long tagId);
	
	
	
	
}
