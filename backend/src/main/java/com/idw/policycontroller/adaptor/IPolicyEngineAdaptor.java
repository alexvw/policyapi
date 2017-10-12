package com.idw.policymanager.adaptor;

import com.idw.policymanager.domain.PolicyDecision;
import com.idw.policymanager.domain.axn.EndPointResponse;
import com.idw.policymanager.util.PolicyManagerException;
/*
 * @author avanderwoude
 */

public interface IPolicyEngineAdaptor {
	
	/**
	 * @param policyName 
	 * @param attributes
	 * @return Map<assertionId, pass|fail|unverified>
	 * @throws PolicyManagerException 
	 * @throws Exception
	 */
	public PolicyDecision makeDecision(EndPointResponse theEndpoint, String policyName) throws PolicyManagerException;

}