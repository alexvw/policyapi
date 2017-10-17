package com.idw.policymanager.exception;

import lombok.Data;
/*
 * @author avanderwoude
 */

/**
 * @author avanderwoude this exception must be caught by service layer and transmitted back as error response
 */
@Data
public class PolicyManagerException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String exception;
	private String errorMessage;
	
	public PolicyManagerException(String errorMessage) {
		super();
		this.exception = null;
		this.errorMessage = errorMessage;
	}
	
	public PolicyManagerException(String exception, String errorMessage) {
		super();
		this.exception = exception;
		this.errorMessage = errorMessage;
	}
}
