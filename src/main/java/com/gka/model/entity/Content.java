package com.gka.model.entity;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.gka.utils.SlugUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="contents")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Content extends BaseEntity {
	
	@Column(name="title")
	private String title;
	
	@Column(name="slug")
	private String slug;
	
	@Column(name="content" , columnDefinition = "TEXT")
	private String content;
	
	@Column(name="content_type")
	@Enumerated(EnumType.STRING)
	private ContentType contentType;
	
	
	@Column(name="content_status")
	@Enumerated(EnumType.STRING)
	private ContentStatus contentStatus;
	
	@Column(name="publish_date")
	private ZonedDateTime publishDate;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="created_by_id")
	private User createdBy;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="updated_by_id") 
	private User updatedBy;
	
	
	
	@Version
	private Long version; 
	
	@JdbcTypeCode(SqlTypes.JSON)
	@Column(columnDefinition ="jsonb")
	private Map<String, Object> metadata;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="content_categories",
			joinColumns = @JoinColumn(name="content_id"),
			inverseJoinColumns = @JoinColumn(name="category_id")
			)
	private Set<Category> categories = new HashSet<>();
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="content_tags",
			joinColumns = @JoinColumn(name="content_id"),
			inverseJoinColumns = @JoinColumn(name="tag_id")
			
			)
	private Set<Tag> tags = new HashSet<>();
	
	
	@PrePersist
	@PreUpdate
	public void generateSlug()
	{
		 this.slug = SlugUtils.generateSlug(this.title);
	}
	
	
	
	
	
	

}
