package com.idw.policycontroller.adaptor;

import com.idw.policycontroller.domain.PolicyDecision;
import com.idw.policycontroller.domain.axn.EndPointResponse;

public interface IPolicyEngineAdaptor {
	
	/**
	 * @param policyName 
	 * @param attributes
	 * @return Map<assertionId, pass|fail|unverified>
	 * @throws Exception
	 */
	public PolicyDecision makeDecision(EndPointResponse theEndpoint, String policyName);

}