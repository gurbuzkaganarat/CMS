package com.gka.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gka.controller.IRestContentVersionController;
import com.gka.controller.RestBaseController;
import com.gka.controller.RootEntity;
import com.gka.model.response.ContentResponse;
import com.gka.service.IContentVersionService;

@RestController
@RequestMapping("/api/v1/contents")
public class RestContentVersionController  extends RestBaseController implements IRestContentVersionController{

	@Autowired
	private IContentVersionService contentVersionService;
	
	
	@GetMapping("/versions/{originaContentId}")
	@Override
	public RootEntity<List<ContentResponse>> getAllVersionsByContentId(@PathVariable(value="originaContentId") Long originalContentId) {
		
		return ok(contentVersionService.getAllVersionsByContentId(originalContentId));
	}

}
