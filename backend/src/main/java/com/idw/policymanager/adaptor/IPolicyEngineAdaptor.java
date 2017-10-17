package com.idw.policymanager.adaptor;

import com.idw.policymanager.domain.PMConfig;
import com.idw.policymanager.domain.PolicyDecision;
import com.idw.policymanager.domain.axn.EndPointResponse;
import com.idw.policymanager.exception.PolicyManagerException;
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
	 * 
	 * This function takes in a policyName and endpoint, and deals with the entirety of the policy application logic
	 */
	public PolicyDecision makeDecision(EndPointResponse theEndpoint, String policyName) throws PolicyManagerException;
	
	/**
	 * @param config a PMConfig object containing any configuration needed to initialize this piece
	 * This function gets called immediately upon instantiation via the factory. 
	 * Needed because the way the factory is used (new rather than autowire) it doesnt go through the standard spring process
	 */
	public void initialize(PMConfig config) throws PolicyManagerException;

}