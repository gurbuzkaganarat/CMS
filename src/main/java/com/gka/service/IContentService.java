package com.gka.service;



import java.util.List;

import com.gka.model.entity.Content;
import com.gka.model.request.ContentRequest;
import com.gka.model.response.ContentResponse;

public interface IContentService {

	public ContentResponse saveContent(ContentRequest contentRequest);
	
	public Content findContentById(Long contendId);
	
	public ContentResponse getContentById(Long contentId);
	
	public List<ContentResponse> getAllContents();
	
	public ContentResponse updateContent(Long contentId,ContentRequest contentRequest);
	
	public void deleteContentById(Long contentId);
	
	public ContentResponse publishContent(Long contentId) ;
	
	public ContentResponse unpublishContent (Long contentId) ;
	
}
