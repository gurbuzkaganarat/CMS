package com.gka.controller;

import java.util.List;

import com.gka.model.response.ContentResponse;

public interface IRestContentVersionController {
	
	
 public RootEntity<List<ContentResponse>> getAllVersionsByContentId(Long originalContentId);

}
