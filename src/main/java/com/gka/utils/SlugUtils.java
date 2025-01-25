package com.gka.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class SlugUtils {
	
	private static final Pattern NON_ALPHANUMERIC_PATTERN = Pattern.compile("[^\\w\\d-]");
	
	public static String generateSlug(String input) {
		if (input==null || input.isEmpty()) {
			
			return "";
		}
		
		String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
		normalized = normalized.replaceAll("[^\\p{ASCII}]", ""); 
		
		String slug = normalized.trim().toLowerCase();  
        slug = NON_ALPHANUMERIC_PATTERN.matcher(slug).replaceAll("");  
        slug = slug.replaceAll("\\s+", "-");  

       
        return slug;
		
	}
}
