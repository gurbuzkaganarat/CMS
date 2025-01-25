package com.gka.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gka.model.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
	
	Optional<Tag> findById (Long id);
	
	boolean existsByName(String name);

}
