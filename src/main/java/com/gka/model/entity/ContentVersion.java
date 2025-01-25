package com.gka.model.entity;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "content_versions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ContentVersion extends BaseEntity {

	@Column(name = "original_content_id")
    private Long originalContentId;

    @Column(name = "version_number")
    private Long versionNumber;

    @Column(name = "title")
    private String title;

    @Column(name = "slug")
    private String slug;

    @Column(name = "content", columnDefinition = "TEXT")
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
    @JoinColumn(name = "version_created_by_id")
    private User versionCreatedBy;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="version_updated_by_id") 
	private User versionUpdatedBy;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> metadata;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "content_version_categories",
        joinColumns = @JoinColumn(name = "content_version_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "content_version_tags",
        joinColumns = @JoinColumn(name = "content_version_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();
}
