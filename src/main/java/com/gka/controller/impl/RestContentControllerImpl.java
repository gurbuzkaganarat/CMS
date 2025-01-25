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

import com.gka.controller.IRestContentController;
import com.gka.controller.RestBaseController;
import com.gka.controller.RootEntity;
import com.gka.model.request.ContentRequest;
import com.gka.model.response.ContentResponse;
import com.gka.service.IContentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/contents")

public class RestContentControllerImpl extends RestBaseController implements IRestContentController {

	@Autowired
	private IContentService contentService;
	   
	
	@PostMapping("/save")
	@Override
	public RootEntity<ContentResponse> saveContent(@Valid @RequestBody ContentRequest contentRequest) {
		
		return ok(contentService.saveContent(contentRequest));
	}

	@GetMapping("/{contendId}")
	@Override
	public RootEntity<ContentResponse> getContentById(@PathVariable(value="contendId") Long contenId) {
		
		return ok(contentService.getContentById(contenId));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<ContentResponse>> getAllContents() {
		
		return ok(contentService.getAllContents());
	}

	@PutMapping("/{contentId}")
	@Override
	public RootEntity<ContentResponse> updateContent(@PathVariable(value = "contentId")  Long contentId, @RequestBody ContentRequest contentRequest) {
		return ok(contentService.updateContent(contentId, contentRequest));
	}

	@DeleteMapping("/{contentId}")
	@Override
	public void deleteContentById(@PathVariable(value = "contentId") Long contentId) {
	 contentService.deleteContentById(contentId);
		
	}

	@PutMapping(("/{contentId}/publish"))
	@Override
	public  RootEntity<ContentResponse> publishContent(@PathVariable(value = "contentId") Long contentId) {
		
		return ok(contentService.publishContent(contentId));
	}

	@PutMapping(("/{contentId}/unpublish"))
	@Override
	public RootEntity<ContentResponse> unpublishContent(@PathVariable(value = "contentId")  Long contentId) {
		return ok(contentService.unpublishContent(contentId));
	}

	



}
