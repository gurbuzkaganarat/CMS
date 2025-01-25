package com.gka.model.entity;


import com.gka.utils.SlugUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tags")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tag extends BaseEntity {
	
	
	@Column(name="name")
	private String name;
	
	@Column(name = "slug")
	private  String slug;
	
	@PrePersist
	@PreUpdate
	public void generateSlug()
	{
		 this.slug = SlugUtils.generateSlug(this.name);
	}

}
