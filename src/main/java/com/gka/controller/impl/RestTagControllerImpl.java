package com.gka.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gka.controller.IRestTagController;
import com.gka.controller.RestBaseController;
import com.gka.controller.RootEntity;
import com.gka.model.request.TagRequest;
import com.gka.model.response.TagResponse;
import com.gka.service.ITagService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/tags")
public class RestTagControllerImpl  extends RestBaseController implements IRestTagController{

	@Autowired
	private ITagService tagService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<TagResponse> saveTag(@Valid @RequestBody TagRequest tagRequest) {
		
		return ok(tagService.saveTag(tagRequest));
	}

	@GetMapping("/{tagId}")
	@Override
	public RootEntity<TagResponse> getTagById(@PathVariable(value="tagId")  Long tagId) {
		
		
		
		return ok(tagService.getTagById(tagId));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<TagResponse>> getAllTags() {
		
		return ok(tagService.gelAllTags());
	}

	@PutMapping("/{tagId}")
	@Override
	public RootEntity<TagResponse> updateTag( @PathVariable(value="tagId") Long tagId, @RequestBody TagRequest tagRequest) {
		
		return ok(tagService.updateTag(tagId, tagRequest));
	}

	@DeleteMapping("/{tagId}")
	@Override
	public void deleteTagById(@PathVariable(value="tagId") Long tagId) {
		
		tagService.deleteTagById(tagId);
	}
	

}
