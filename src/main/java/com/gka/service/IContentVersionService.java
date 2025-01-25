package com.gka.service;

import java.util.List;

import com.gka.model.entity.Content;
import com.gka.model.entity.ContentVersion;
import com.gka.model.response.ContentResponse;

public interface IContentVersionService {

	public ContentVersion createVersionBackup(Content content);
	
	public List<ContentVersion> getVersionHistory(Long contentId);
	
	List<ContentResponse> getAllVersionsByContentId(Long originalContentId);
}
