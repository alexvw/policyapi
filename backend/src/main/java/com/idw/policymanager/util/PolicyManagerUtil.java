package com.idw.policymanager.util;

import com.idw.policymanager.domain.axn.EndPointResponse;

public class PolicyManagerUtil {
	//useful common endpoint creator
	public static EndPointResponse createDummyEndpoint(){
		EndPointResponse theEndpoint = new EndPointResponse();
		theEndpoint.setCredential("test@website.com");
		theEndpoint.setMbun("123456");
		//TODO: add assertions for testing
		
		return theEndpoint;
	}

}
