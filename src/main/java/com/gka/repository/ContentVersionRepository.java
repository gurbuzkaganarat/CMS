package com.gka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gka.model.entity.ContentVersion;

@Repository
public interface ContentVersionRepository  extends JpaRepository<ContentVersion, Long>{
	
	List<ContentVersion> findByOriginalContentIdOrderByVersionNumberDesc(Long orinalContentId);
	
	

}
