package com.gka.model.response;

import com.gka.model.dto.BaseDto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class TagResponse  extends BaseDto{
	
	private String name;
	
	private  String slug;
	
	

}
