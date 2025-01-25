package com.gka.model.dto;

import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class BaseDto {
	
	private Long id;
	
	private ZonedDateTime createdDateTime;
	
	private ZonedDateTime modifiedDateTime;

}
