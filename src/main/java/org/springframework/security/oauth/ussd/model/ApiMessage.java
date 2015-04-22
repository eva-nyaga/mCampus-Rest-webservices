package org.springframework.security.oauth.ussd.model;


/**
 * A wrapper class for showing a message to the 
 * @author mulama
 *
 */
public class ApiMessage {
	
	private String message;
	
	public ApiMessage(String msg){
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}
}
