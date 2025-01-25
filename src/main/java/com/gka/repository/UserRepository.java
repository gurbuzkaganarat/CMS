package com.gka.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gka.model.entity.User;


public interface UserRepository  extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email );
	
	Optional<User> findByUsername(String username);
	
	Optional<User>  findById(Long id);
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
	

}
