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

import com.gka.controller.IRestCategoryController;
import com.gka.controller.RestBaseController;
import com.gka.controller.RootEntity;
import com.gka.model.request.CategoryRequest;
import com.gka.model.response.CategoryResponse;
import com.gka.service.ICategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class RestCategoryControllerImpl extends RestBaseController implements IRestCategoryController {

	@Autowired
	private ICategoryService categoryService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<CategoryResponse> saveCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
		
		return ok(categoryService.saveCategory(categoryRequest));
	}

	@GetMapping("/{categoryId}")
	@Override
	public RootEntity<CategoryResponse> getCategoryById(@PathVariable(value="categoryId") Long categoryId) {
		
		return ok(categoryService.getCategoryById(categoryId));
	}

	@GetMapping("/{categoryId}/subcategories")
	@Override
	public RootEntity<CategoryResponse> getCategoryByIdWithSubCategories( @PathVariable(value="categoryId") Long categoryId) {
		return ok(categoryService.getCategoryById(categoryId));
	}

	@DeleteMapping("/{categoryId}")
	@Override
	public void deleteCategoryById(@PathVariable(value="categoryId") Long categoryId) {
		categoryService.deleteCategoryById(categoryId);;
		
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<CategoryResponse>> getAllCategories() {
		
		return ok(categoryService.getAllCategories());
	}

	@PutMapping("/{categoryId}")
	@Override
	public RootEntity<CategoryResponse> updateCategory(@PathVariable(value="categoryId")  Long categoryId, @RequestBody CategoryRequest categoryRequest) {
		
		return ok(categoryService.updateCategory(categoryId, categoryRequest));
	}

}
