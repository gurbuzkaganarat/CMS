package com.gka.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gka.mapper.ContentMapper;
import com.gka.model.entity.Content;
import com.gka.model.entity.ContentVersion;
import com.gka.model.response.ContentResponse;
import com.gka.repository.ContentVersionRepository;
import com.gka.service.IContentVersionService;

@Service
public class ContentVersionServiceImpl  implements IContentVersionService{
	

	
	@Autowired ContentVersionRepository contentVersionRepository;

	@Override
	public ContentVersion createVersionBackup(Content content) {
		
		Long lastVersionNumber = contentVersionRepository
	            .findByOriginalContentIdOrderByVersionNumberDesc(content.getId())
	            .stream()
	            .mapToLong(ContentVersion::getVersionNumber)
	            .max()
	            .orElse(0L);
		
		Long newVersionNumber = lastVersionNumber +1;
		
		ContentVersion version = new ContentVersion();
	    version.setOriginalContentId(content.getId());
	    version.setTitle(content.getTitle());
	    version.setContent(content.getContent());
	    version.setContentType(content.getContentType());
	    version.setContentStatus(content.getContentStatus());
	    version.setSlug(content.getSlug());
	    version.setVersionCreatedBy(content.getCreatedBy());
	    version.setVersionUpdatedBy(content.getUpdatedBy());
	    version.setMetadata(content.getMetadata());
	    version.setCategories(
	            content.getCategories().stream().collect(Collectors.toSet())
	        );
	        version.setTags(
	            content.getTags().stream().collect(Collectors.toSet())
	        );

	        version.setVersionNumber(newVersionNumber);
	        return contentVersionRepository.save(version);
		
		
	}

	@Override
	public List<ContentVersion> getVersionHistory(Long contentId) {
		return contentVersionRepository.findByOriginalContentIdOrderByVersionNumberDesc(contentId);
		
	}

	@Override
	public List<ContentResponse> getAllVersionsByContentId(Long originalContentId) {
		List<ContentVersion> versions = contentVersionRepository.findByOriginalContentIdOrderByVersionNumberDesc(originalContentId);
		return versions.stream()
				.map(ContentMapper::toResponse)
				.collect(Collectors.toList());
	}

}
