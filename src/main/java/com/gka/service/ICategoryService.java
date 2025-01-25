package com.gka.service;

import java.util.List;

import com.gka.model.entity.Category;
import com.gka.model.request.CategoryRequest;
import com.gka.model.response.CategoryResponse;

public interface ICategoryService {
	
	
	public  CategoryResponse saveCategory(CategoryRequest categoryRequest);
	
	public Category findCategoryById(Long categoryId);
	
	public CategoryResponse getCategoryById(Long categoryId);
	
	public CategoryResponse getCategoryByIdWithSubCategories(Long categoryId);
	
	public List<CategoryResponse> getAllCategories();
	
	public CategoryResponse updateCategory(Long categoryId , CategoryRequest categoryRequest);
	
	public void deleteCategoryById(Long categoryId);

}
