package com.gka.controller;

import java.util.List;

import com.gka.model.request.CategoryRequest;
import com.gka.model.response.CategoryResponse;

public interface IRestCategoryController {
	
	public RootEntity<CategoryResponse> saveCategory (CategoryRequest categoryRequest);
	
	public RootEntity<CategoryResponse>  getCategoryById(Long categoryId);
	
	public RootEntity<CategoryResponse> getCategoryByIdWithSubCategories(Long categoryId);
	
	public RootEntity<List<CategoryResponse>> getAllCategories();
	
	public RootEntity<CategoryResponse>  updateCategory(Long categoryId , CategoryRequest categoryRequest);
	
	public void deleteCategoryById(Long categoryId);
	
	

}
