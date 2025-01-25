package com.gka.exception;

import lombok.Getter;

@Getter
public enum MessageType {
    
	
	
	
	
    BAD_REQUEST(400, "Bad request"),
    NOT_FOUND(404, "Resource not found"),
    VALIDATION(422, "Validation error"),
    UNAUTHORIZED(401, "Unauthorized access"),
    FORBIDDEN(403, "Access forbidden"),
    CONFLICT(409, "Conflıct"),
    INTERNAL_ERROR(500, "Internal server error"),
	CODE_EXPIRED(410,"The verification code has expired");
    
    private final int httpCode;
    private final String message;
    
    
    MessageType(int httpCode, String message) {
        this.httpCode = httpCode;
        this.message = message;
    }
}
