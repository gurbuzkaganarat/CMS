package com.gka.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gka.exception.BaseException;
import com.gka.exception.ErrorMessage;
import com.gka.exception.MessageType;
import com.gka.mapper.CategoryMapper;
import com.gka.model.entity.Category;
import com.gka.model.entity.Content;
import com.gka.model.request.CategoryRequest;
import com.gka.model.response.CategoryResponse;
import com.gka.repository.CategoryRepository;
import com.gka.repository.ContentRepository;
import com.gka.service.ICategoryService;

import jakarta.transaction.Transactional;

@Service
public class CategoryServiceImpl implements ICategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ContentRepository contentRepository;

	private Category createCategory(CategoryRequest categoryRequest) {
		
		Category category = new Category();
		category.setName(categoryRequest.getName());
		category.setDescription(categoryRequest.getDescription());
		if (categoryRequest.getParentId() != null) {
	        Optional<Category> parentCategory = categoryRepository.findById(categoryRequest.getParentId());
	        if (parentCategory.isEmpty()) {
	            throw new BaseException(
	                    new ErrorMessage(
	                            MessageType.NOT_FOUND, "Parent Category not found with id : " + categoryRequest.getParentId()));
	        }
	        category.setParent(parentCategory.get());
	    } else {
	        category.setParent(null);  
	    }
		
		
		return category;
	}
	
	@Override
	public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
		
		if (categoryRepository.existsByName(categoryRequest.getName())) {
			throw new BaseException(
					new ErrorMessage(MessageType.CONFLICT, "Category name already exist : " + categoryRequest.getName()));
		}
		
		Category savedCategory = categoryRepository.save(createCategory(categoryRequest));
		
		return CategoryMapper.toResponse(savedCategory,false);
	}

	@Override
	public Category findCategoryById(Long categoryId) {
		
		return categoryRepository.findById(categoryId)
				.orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NOT_FOUND, "Category not found" )));
	}

	@Override
	public CategoryResponse getCategoryById(Long categoryId) {
		
		Category category = findCategoryById(categoryId);
		
		return CategoryMapper.toResponse(category,false);
	}

	@Override
	public CategoryResponse getCategoryByIdWithSubCategories(Long categoryId) {
		 Category category = findCategoryById(categoryId);
	        return CategoryMapper.toResponse(category, true);
	}

	@Override
	public List<CategoryResponse> getAllCategories() {
		List<Category> categoryList =categoryRepository.findAll();
		return categoryList.stream()
				.map(category -> CategoryMapper.toResponse(category, false))
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public void deleteCategoryById(Long categoryId) {
		
		Category category = findCategoryById(categoryId);
		  
	    if (!category.getSuCategories().isEmpty()) {
	        throw new BaseException(
	            new ErrorMessage(MessageType.BAD_REQUEST, "Category has subcategories and cannot be deleted")
	        );
	    }
	    
	    Set<Content> relatedContents= contentRepository.findByCategoriesContaining(category);
	    for (Content content : relatedContents) {
			content.getCategories().remove(category);
			contentRepository.save(content);
		}
		
		categoryRepository.delete(category);
		
	}

	@Override
	public CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest) {
		Category category = findCategoryById(categoryId);
		
		if (categoryRequest.getParentId() != null && categoryRequest.getParentId().equals(categoryId)) {
			 throw new BaseException(
			            new ErrorMessage(MessageType.BAD_REQUEST, "A category cannot be its own parent"));
		}
		
		if (categoryRequest.getParentId() != null) {
			Optional<Category> parentCategory = categoryRepository.findById(categoryRequest.getParentId());
			if (parentCategory.isEmpty()) {
				throw new BaseException(
		                new ErrorMessage(MessageType.NOT_FOUND, "Parent Category not found with id: " + categoryRequest.getParentId()));
			}
			category.setParent(parentCategory.get());
		}
		
		if (categoryRequest.getName() != null) {
		    category.setName(categoryRequest.getName());  
		}
		if (categoryRequest.getDescription() != null) {
		    category.setDescription(categoryRequest.getDescription()); 
		}
		
		categoryRepository.save(category);
	
		return CategoryMapper.toResponse(category, false);
	}
	
	

}
