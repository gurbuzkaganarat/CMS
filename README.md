# Content Management System Backend Requirements

## Project Overview
Develop a RESTful backend service for a Content Management System using Spring Boot. The system will manage various types of content (articles, pages) along with their associated metadata, categories, and tags. The system should support content versioning, drafts, and scheduled publishing.

## Technical Stack
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL 15+
- Lombok
- Spring Validation
- Maven/Gradle (build tool)

## Core Features

### 1. Content Management
- Support multiple content types (articles, pages)
- Content versioning with audit trails
- Draft/Published status management
- Scheduled publishing
- Content archiving
- Rich text content support
- SEO metadata management

### 2. Category & Tag Management
- Hierarchical category structure
- Multiple tag support
- Category and tag statistics
- Slug generation for URLs

### 3. User & Permission System
- Role-based access control (Admin, Editor, Author)
- User management
- Permission management
- Action logging

## Database Entities

### Content
```java
- id (Long)
- title (String)
- slug (String, unique)
- content (Text)
- contentType (Enum: ARTICLE, PAGE)
- status (Enum: DRAFT, PUBLISHED, ARCHIVED)
- publishDate (LocalDateTime)
- createdAt (LocalDateTime)
- updatedAt (LocalDateTime)
- createdBy (User)
- updatedBy (User)
- version (Integer)
- metadata (JSON)
- categories (Set<Category>)
- tags (Set<Tag>)
```

### Category
```java
- id (Long)
- name (String)
- slug (String, unique)
- description (String)
- parent (Category, self-referential)
- children (Set<Category>)
- createdAt (LocalDateTime)
- updatedAt (LocalDateTime)
```

### Tag
```java
- id (Long)
- name (String)
- slug (String, unique)
- createdAt (LocalDateTime)
- updatedAt (LocalDateTime)
```

### User
```java
- id (Long)
- username (String, unique)
- email (String, unique)
- password (String, encrypted)
- role (Enum: ADMIN, EDITOR, AUTHOR)
- status (Enum: ACTIVE, INACTIVE)
- createdAt (LocalDateTime)
- updatedAt (LocalDateTime)
```

## API Endpoints

### Content Endpoints
```
GET     /api/v1/contents                  # List all contents with pagination and filters
POST    /api/v1/contents                  # Create new content
GET     /api/v1/contents/{id}             # Get content by ID
PUT     /api/v1/contents/{id}            # Update content
DELETE  /api/v1/contents/{id}            # Delete/Archive content
GET     /api/v1/contents/versions/{id}    # Get content version history
POST    /api/v1/contents/{id}/publish     # Publish content
POST    /api/v1/contents/{id}/unpublish   # Unpublish content
```

### Category Endpoints
```
GET     /api/v1/categories               # List all categories
POST    /api/v1/categories               # Create new category
GET     /api/v1/categories/{id}          # Get category by ID
PUT     /api/v1/categories/{id}          # Update category
DELETE  /api/v1/categories/{id}          # Delete category
GET     /api/v1/categories/{id}/contents # Get contents by category
```

### Tag Endpoints
```
GET     /api/v1/tags                     # List all tags
POST    /api/v1/tags                     # Create new tag
GET     /api/v1/tags/{id}                # Get tag by ID
PUT     /api/v1/tags/{id}                # Update tag
DELETE  /api/v1/tags/{id}                # Delete tag
GET     /api/v1/tags/{id}/contents       # Get contents by tag
```

### User Endpoints
```
GET     /api/v1/users                    # List all users
POST    /api/v1/users                    # Create new user
GET     /api/v1/users/{id}               # Get user by ID
PUT     /api/v1/users/{id}               # Update user
DELETE  /api/v1/users/{id}               # Delete user
```

## Technical Requirements

### 1. Security
- Implement JWT-based authentication
- Password encryption using BCrypt
- Input validation and sanitization
- Role-based access control
- API rate limiting

### 2. Database
- Implement database migrations
- Use appropriate indexes for optimization
- Implement soft delete where appropriate
- Handle database transactions properly

### 3. Validation
- Input validation using Jakarta Validation
- Custom validation annotations where needed
- Error handling and validation messages

### 4. Documentation
- API documentation using SpringDoc OpenAPI
- Code documentation using Javadoc
- README with setup instructions
- Postman/Insomnia collection for API testing

### 5. Performance
- Implement caching where appropriate
- Pagination for list endpoints
- Optimize database queries

## Development Guidelines

### 1. Code Organization
- Follow clean architecture principles
- Implement proper separation of concerns
- Use DTOs for request/response objects
- Implement mapper classes for entity-DTO conversion

### 2. Error Handling
- Implement global exception handling
- Create custom exceptions
- Proper error messages and codes
- Logging of errors and exceptions

### 3. Best Practices
- Follow REST API best practices
- Implement proper logging
- Use constants for magic numbers and strings
- Follow Java code conventions
