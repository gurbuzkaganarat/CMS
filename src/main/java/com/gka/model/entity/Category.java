package com.gka.model.entity;

import java.util.HashSet;
import java.util.Set;

import com.gka.utils.SlugUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Category extends BaseEntity {

	@Column(name="name")
	private String name;
	
	@Column(name="slug")
	private String slug;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Category parent;
	
	 @OneToMany(mappedBy = "parent" )
	    private Set<Category> suCategories  = new HashSet<>();
	 
	 	@PrePersist
		@PreUpdate
		public void generateSlug()
		{
			 this.slug = SlugUtils.generateSlug(this.name);
		}
	
}
