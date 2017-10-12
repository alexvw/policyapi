package com.idw.policycontroller.adaptor.dummy;

import com.idw.policycontroller.adaptor.IPolicyEngineAdaptor;
import com.idw.policycontroller.domain.PolicyDecision;
import com.idw.policycontroller.domain.axn.EndPointResponse;

public class DummyAdaptor implements IPolicyEngineAdaptor{
	
	/*
	 * Dummy adaptor for testing. will always permit or deny based on policyname
	 * */
	@Override
	public PolicyDecision makeDecision(EndPointResponse theEndpoint, String policyName) {
		PolicyDecision theVerdict = new PolicyDecision();
		if (policyName.equals("alwaysPermit")){
			theVerdict.permit();
		}
		else if (policyName.equals("alwaysDeny")){
			theVerdict.deny();
		}
		theVerdict.setTheEndpointData(theEndpoint);
		
		return theVerdict;
	}

}
