package com.gka.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

	 private MessageType messageType;
	 private String additionalMessage;
   

    public String prepareErrorMessage() {
    	StringBuilder builder = new StringBuilder();
		builder.append(messageType.getMessage());
		if(this.additionalMessage!=null) {
			builder.append(" : " + additionalMessage);
		}
		return builder.toString();
    }
}
