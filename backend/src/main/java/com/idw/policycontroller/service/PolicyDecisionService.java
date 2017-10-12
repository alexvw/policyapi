package com.idw.policycontroller.service;

import com.idw.policycontroller.domain.Policy;
import com.idw.policycontroller.domain.PolicyDecision;
import com.idw.policycontroller.domain.PolicyEngine;
import com.idw.policycontroller.domain.axn.EndPointResponse;
import com.idw.policycontroller.util.PolicyControllerException;

public interface PolicyDecisionService {
	
	/*
	 * @param apikey the apikey to search for
	 * @return Policy the policy selected for this apikey
	 */
	public Policy getPolicyForApiKey(String apiKey) throws PolicyControllerException;

	/*
	 * @param theEndpoint the endpoint data to make a decision on
	 * @param apiKey the apikey this endpoint data is for
	 */
	public PolicyDecision getDecisionForEndpoint(EndPointResponse theEndpoint, String apiKey) throws PolicyControllerException;

	/*
	 * @param peid the Long id of the PolicyEngine to get
	 * @return policyengine the policyengine object found
	 */
	public PolicyEngine getPolicyEngineForPolicy(Long peid) throws PolicyControllerException;

}
