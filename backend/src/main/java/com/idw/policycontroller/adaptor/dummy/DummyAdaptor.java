package com.idw.policymanager.adaptor.dummy;

import com.idw.policymanager.adaptor.IPolicyEngineAdaptor;
import com.idw.policymanager.domain.PolicyDecision;
import com.idw.policymanager.domain.axn.EndPointResponse;
/*
 * @author avanderwoude
 */

public class DummyAdaptor implements IPolicyEngineAdaptor{
	
	/*
	 * Dummy adaptor for testing. will always approve or deny based on policyname
	 * */
	@Override
	public PolicyDecision makeDecision(EndPointResponse theEndpoint, String policyName) {
		PolicyDecision theVerdict = new PolicyDecision();
		if (policyName.equals("alwaysApprove")){
			theVerdict.approve();
		}
		else if (policyName.equals("alwaysDeny")){
			theVerdict.deny();
		}
		theVerdict.setTheEndpointData(theEndpoint);
		
		return theVerdict;
	}

}
