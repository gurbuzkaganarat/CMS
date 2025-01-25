package com.gka.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gka.model.entity.Category;
import com.gka.model.entity.Content;
import com.gka.model.entity.Tag;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
	
	Optional<Content> findById(Long id);
	
	boolean existsByTitle(String title); 
	
	Set<Content> findByCategoriesContaining(Category category);
	
	Set<Content> findByTagsContaining(Tag tag);

}
