package com.gka.service.impl;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gka.exception.BaseException;
import com.gka.exception.ErrorMessage;
import com.gka.exception.MessageType;
import com.gka.jwt.AuthenticationFacade;
import com.gka.mapper.ContentMapper;
import com.gka.model.entity.Category;
import com.gka.model.entity.Content;
import com.gka.model.entity.ContentStatus;
import com.gka.model.entity.Tag;
import com.gka.model.entity.User;
import com.gka.model.request.ContentRequest;
import com.gka.model.response.ContentResponse;
import com.gka.repository.CategoryRepository;
import com.gka.repository.ContentRepository;
import com.gka.repository.TagRepository;
import com.gka.service.IContentService;
import com.gka.service.IContentVersionService;

import jakarta.transaction.Transactional;

@Service
public class ContentServiceImpl implements IContentService {

	
	@Autowired
	private ContentRepository contentRepository;
	
	  @Autowired
	  private IContentVersionService contentVersionService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private  AuthenticationFacade authenticationFacade;
	
	

	
	private Content setUserFields(Content content) {
       
        User currentUser = authenticationFacade.getUserThroughAuthentication(SecurityContextHolder.getContext().getAuthentication());

        
        if (content.getCreatedBy() == null) {
            content.setCreatedBy(currentUser);
        }
        content.setUpdatedBy(currentUser);

        return content;
    }
	

	
	 private Content createContent(ContentRequest contentRequest) {
	        Content content = new Content();
	        
	       
	        
	     
	        content.setTitle(contentRequest.getTitle());
	        content.setContent(contentRequest.getContent());
	        content.setContentType(contentRequest.getContentType());
	        content.setContentStatus(ContentStatus.DRAFT);
	        content.setMetadata(contentRequest.getMetadata());
	        content = setUserFields(content);
	        
	        
	        
	        
	        // Set categories
	        List<Category> categories = categoryRepository.findAllById(contentRequest.getCategoryIds());
	        if (categories.size() != contentRequest.getCategoryIds().size()) {
	            throw new BaseException(
	                new ErrorMessage(MessageType.NOT_FOUND, "Some categories were not found.")
	            );
	        }
	        content.setCategories(new HashSet<>(categories));
	        
	        // Set tags if provided
	        if (contentRequest.getTagIds() != null && !contentRequest.getTagIds().isEmpty()) {
	            List<Tag> tags = tagRepository.findAllById(contentRequest.getTagIds());
	            if (tags.size() != contentRequest.getTagIds().size()) {
	                throw new BaseException(
	                    new ErrorMessage(MessageType.NOT_FOUND, "Some tags were not found.")
	                );
	            }
	            content.setTags(new HashSet<>(tags));
	        }
	        
	        return content;
	    }
	
	
	@Transactional
	@Override
	public ContentResponse saveContent(ContentRequest contentRequest) {
		
		
		 // İlk olarak Content nesnesini oluştur
	    Content content = createContent(contentRequest);

	    // Content kaydını gerçekleştir
	    Content savedContent = contentRepository.save(content);

	    // Versiyon yedeği oluştur
	    contentVersionService.createVersionBackup(savedContent);
		return ContentMapper.toResponse(savedContent);
	}


	@Override
	public Content findContentById(Long contendId) {
		
		return contentRepository.findById(contendId)
				.orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NOT_FOUND, "Content not found" )));
	}


	@Override
	public ContentResponse getContentById(Long contenId) {
		
		Content content = findContentById(contenId);
		
		return ContentMapper.toResponse(content);
	}


	@Override
	public List<ContentResponse> getAllContents() {
		List<Content> contentList = contentRepository.findAll();
		return contentList.stream()
				.map(ContentMapper::toResponse)
				.collect(Collectors.toList());
	}

	 	@Transactional
	    @Override
	    
	    public ContentResponse updateContent(Long contentId, ContentRequest contentRequest) {
	        
		 Content content = findContentById(contentId);
		 content = setUserFields(content);
		 contentVersionService.createVersionBackup(content);

		    
		    if (contentRequest.getTitle() != null && !contentRequest.getTitle().isEmpty()) {
		        content.setTitle(contentRequest.getTitle());
		    }

		    
		    if (contentRequest.getContent() != null && !contentRequest.getContent().isEmpty()) {
		        content.setContent(contentRequest.getContent());
		    }

		   
		    if (contentRequest.getContentType() != null) {
		        content.setContentType(contentRequest.getContentType());
		    }

		    
		    if (contentRequest.getCategoryIds() != null && !contentRequest.getCategoryIds().isEmpty()) {
		        List<Category> categories = categoryRepository.findAllById(contentRequest.getCategoryIds());
		        if (categories.size() != contentRequest.getCategoryIds().size()) {
		            throw new BaseException(
		                new ErrorMessage(MessageType.NOT_FOUND, "Some categories were not found.")
		            );
		        }
		        content.setCategories(new HashSet<>(categories));
		    }

		    
		    if (contentRequest.getTagIds() != null && !contentRequest.getTagIds().isEmpty()) {
		        List<Tag> tags = tagRepository.findAllById(contentRequest.getTagIds());
		        if (tags.size() != contentRequest.getTagIds().size()) {
		            throw new BaseException(
		                new ErrorMessage(MessageType.NOT_FOUND, "Some tags were not found.")
		            );
		        }
		        content.setTags(new HashSet<>(tags));
		    }

		   
		    if (contentRequest.getMetadata() != null && !contentRequest.getMetadata().isEmpty()) {
		        content.setMetadata(contentRequest.getMetadata());
		    }
		    
		   		   
		    	
		    return ContentMapper.toResponse(contentRepository.save(content)); 
	    }

	



	@Override
	public void deleteContentById(Long contentId) {
		
		Content content = findContentById(contentId);
		
		contentRepository.delete(content);
		
	}


	@Transactional
	@Override
	public ContentResponse publishContent(Long contentId) {
		
		Content content = findContentById(contentId);
		content = setUserFields(content);
		
		if (ContentStatus.PUBLISHED.equals(content.getContentStatus())) {
			throw new BaseException(
		            new ErrorMessage(MessageType.VALIDATION, "Content is already published."));
		}
		
		contentVersionService.createVersionBackup(content);
		content.setContentStatus(ContentStatus.PUBLISHED);
		content.setPublishDate(ZonedDateTime.now());
		contentRepository.saveAndFlush(content);
		
		
		return ContentMapper.toResponse(content);
	}


	@Override
	public ContentResponse unpublishContent(Long contentId) {
		Content content = findContentById(contentId);

	   
	    if (!ContentStatus.PUBLISHED.equals(content.getContentStatus())) {
	        throw new BaseException(
	            new ErrorMessage(MessageType.VALIDATION, "Content is not currently published.")
	        );
	    }

	    
	    content.setContentStatus(ContentStatus.DRAFT);
	    content.setPublishDate(null); 

	    
	    Content updatedContent = contentRepository.save(content);

	    
	    return ContentMapper.toResponse(updatedContent);
	}




}
