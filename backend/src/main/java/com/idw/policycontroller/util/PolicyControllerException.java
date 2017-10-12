package com.idw.policycontroller.util;

import lombok.Data;

/**
 * @author avanderwoude this exception must be caught by service layer and transmitted back as error response
 */
@Data
public class PolicyControllerException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String exception;
	private String errorMessage;
	
	public PolicyControllerException(String errorMessage) {
		super();
		this.exception = null;
		this.errorMessage = errorMessage;
	}
	
	public PolicyControllerException(String exception, String errorMessage) {
		super();
		this.exception = exception;
		this.errorMessage = errorMessage;
	}
}
