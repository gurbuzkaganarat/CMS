package com.gka.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gka.exception.BaseException;
import com.gka.exception.ErrorMessage;
import com.gka.exception.MessageType;
import com.gka.mapper.TagMapper;
import com.gka.model.entity.Content;
import com.gka.model.entity.Tag;
import com.gka.model.request.TagRequest;
import com.gka.model.response.TagResponse;
import com.gka.repository.ContentRepository;
import com.gka.repository.TagRepository;
import com.gka.service.ITagService;

import jakarta.transaction.Transactional;

@Service
public class TagServiceImpl implements ITagService {

	
	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private ContentRepository contentRepository;
	
	
	private Tag createTag(TagRequest tagRequest) {
		
		Tag tag = new Tag();
		tag.setName(tagRequest.getName());
		
			 
		return tag;
		
		
	}

	@Override
	public TagResponse saveTag(TagRequest tagRequest) {
		
		if (tagRepository.existsByName(tagRequest.getName())) {
			throw new BaseException(new ErrorMessage(MessageType.CONFLICT, "tag name already exist : " + tagRequest.getName()));
			
		}
		
		
		Tag savedTag = tagRepository.save(createTag(tagRequest));
		
	
		
		
		return TagMapper.toResponse(savedTag);
	}

	@Override
	public Tag findTagById(Long tagId) {
		
		return tagRepository.findById(tagId)
				.orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NOT_FOUND, "Tag not found" )));
	}
	
	

	@Override
	public TagResponse getTagById(Long tagId) {
		 Tag tag = findTagById(tagId);
		return TagMapper.toResponse(tag);
	}

	
	@Override
	public List<TagResponse> gelAllTags() {
		List<Tag> tagList = tagRepository.findAll();
		
		return tagList.stream()
				.map(TagMapper::toResponse)
				.collect(Collectors.toList());
	}

	@Override
	public TagResponse updateTag(Long tagId, TagRequest tagRequest) {
		
		Tag tag = findTagById(tagId);
		tag.setName(tagRequest.getName());
		tagRepository.save(tag);
		
		return TagMapper.toResponse(tag);
	}

	
	@Transactional
	@Override
	public void deleteTagById(Long tagId) {
		 Tag tag = findTagById(tagId);
		 
		 Set<Content> relatedContents = contentRepository.findByTagsContaining(tag);
		 for (Content content : relatedContents) {
			content.getTags().remove(tag);
			contentRepository.save(content);
		}
		 
		 tagRepository.delete(tag);
		
	}
	
	
	
	
	
}
