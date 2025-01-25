package com.gka.controller;

import java.util.List;

import com.gka.model.request.TagRequest;
import com.gka.model.response.TagResponse;

public interface IRestTagController {

	public RootEntity<TagResponse>  saveTag(TagRequest tagRequest);
	
	public RootEntity<TagResponse> getTagById(Long tagId);
	
	public RootEntity<List<TagResponse>> getAllTags();
	
	public RootEntity<TagResponse> updateTag(Long tagId, TagRequest tagRequest);
	
	public void deleteTagById(Long tagId);
}

