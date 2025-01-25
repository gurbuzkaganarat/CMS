package com.gka.controller;

import java.util.List;

import com.gka.model.request.ContentRequest;
import com.gka.model.response.ContentResponse;

public interface IRestContentController {
	
	public RootEntity<ContentResponse> saveContent(ContentRequest contentRequest);
	
	public RootEntity<ContentResponse>  getContentById(Long contenId);
	
	public RootEntity<List<ContentResponse>>  getAllContents();
	
	public RootEntity<ContentResponse> updateContent (Long contentId , ContentRequest contentRequest);
	
	public void deleteContentById(Long contentId);
	
	
	public RootEntity<ContentResponse>  publishContent(Long contentId);
	
	public RootEntity<ContentResponse>  unpublishContent(Long contentId);
	
	
	
	
	

}
