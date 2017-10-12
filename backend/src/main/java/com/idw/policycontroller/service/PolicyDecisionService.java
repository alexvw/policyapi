package com.idw.policymanager.service;

import com.idw.policymanager.domain.Policy;
import com.idw.policymanager.domain.PolicyDecision;
import com.idw.policymanager.domain.PolicyEngine;
import com.idw.policymanager.domain.axn.EndPointResponse;
import com.idw.policymanager.util.PolicyManagerException;
/*
 * @author avanderwoude
 */

public interface PolicyDecisionService {
	
	/*
	 * @param apikey the apikey to search for
	 * @return Policy the policy selected for this apikey
	 */
	public Policy getPolicyForApiKey(String apiKey) throws PolicyManagerException;

	/*
	 * @param theEndpoint the endpoint data to make a decision on
	 * @param apiKey the apikey this endpoint data is for
	 */
	public PolicyDecision getDecisionForEndpoint(EndPointResponse theEndpoint, String apiKey) throws PolicyManagerException;

	/*
	 * @param peid the Long id of the PolicyEngine to get
	 * @return policyengine the policyengine object found
	 */
	public PolicyEngine getPolicyEngineForPolicy(Long peid) throws PolicyManagerException;

}
