package com.idw.policymanager.adaptor.plainid;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idw.policymanager.adaptor.IPolicyEngineAdaptor;
import com.idw.policymanager.adaptor.PolicyEngineBaseAdaptor;
import com.idw.policymanager.domain.PolicyDecision;
import com.idw.policymanager.domain.axn.EndPointResponse;
import com.idw.policymanager.enumerator.DecisionType;
import com.idw.policymanager.util.PolicyManagerException;
/*
 * @author avanderwoude
 */

public class PlainIDAdaptor extends PolicyEngineBaseAdaptor implements IPolicyEngineAdaptor {

	/*
	 * Dummy adaptor for testing. will always approve or deny based on policyname
	 * */
	@Override
	public PolicyDecision makeDecision(EndPointResponse theEndpoint, String policyName) throws PolicyManagerException {
		PolicyDecision theVerdict = new PolicyDecision();
		
		//TODO: move to s3
		final String url = "http://104.198.234.207:9082/plainid-accesslist/token";
		final String clientId = "LJCG2QLRFEC08Z2DD7ID";
		final String clientSecret = "iFc6B6a9g)*zk4tmHdFGXgL7%2QLhwaVmMiF3Wo*";
		//initial configuration
		
		String mbun = theEndpoint.getMbun();
		/*
		 * {
			"entityId": "WayneB",
			"clientId":"LJCG2QLRFEC08Z2DD7ID",
			"clientSecret":"iFc6B6a9g)*zk4tmHdFGXgL7%2QLhwaVmMiF3Wo*",
			"environment": {
			     "DeviceIDApprove": ["Pass"]
			  }
			}
		 */
		String responseString = "";
		
		//build the request
		PlainIDRequest theRequestBody = new PlainIDRequest(mbun, clientId, clientSecret);
		
		//TODO: actually integrate endpoint
		Map dummyEnvironment = new HashMap<String, String>();
		dummyEnvironment.put("DeviceIDApprove", "Pass");
		theRequestBody.setEnvironment(dummyEnvironment);
		
		//make request
		try {
			responseString = sendPOSTRequest(url, theRequestBody);
		} catch (Exception e) {
			//convert to proper exception
			throw new PolicyManagerException("An error occurred while trying to send the request to TMX: "+e.getMessage());
		}
		
		//parse response to object
		/*
		 * {
		    "tokenValidity": 0,
		    "response": [
		        {
		            "contextData": null,
		            "access": [
		                {
		                    "path": "//operation/authenticate",
		                    "actions": [
		                        {
		                            "action": "approve",
		                            "constraints": []
		                        }
		                    ],
		                    "attributes": {
		                        "Name": [
		                            "Authentication"
		                        ]
		                    }
		                }
		            ]
		        }
		    ]
		}
		 */
		//attempt to parse response
		PlainIDResponse theResponse;
		try {
			theResponse = (PlainIDResponse) parseObjectFromJSON(responseString, new PlainIDResponse());
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
			throw new PolicyManagerException("There was a problem parsing the response from PlainID: "+e.getMessage());
		}
		//handle response data 
		//find the 'action' from the json response. Should be approve, deny, or obligation
		//down the json rabbit hole we go...
		String decisionString = theResponse.getResponse().get(0).getAccess().get(0).getActions().get(0).getAction();
		//normailize to enum
		DecisionType decision = DecisionType.valueOf(decisionString);
		
		if (decision == null)
			throw new PolicyManagerException("Policy decision of invalid type: "+decisionString);
		
		if (decision == DecisionType.obligation)
		{
			//TODO: handle obligations
		}
		
		//build return object
		theVerdict.setConclusion(decision);
		theVerdict.setTheEndpointData(theEndpoint);
		theVerdict.setStatus("success");

		return theVerdict;
	}

	/**
	 * @param no args
	 */
	//TODO add this functionality to the tester class
	public static void main(String[] args) {
		// test the tmx adaptor with some basic attributes
		PlainIDAdaptor pidAdaptor = new PlainIDAdaptor();
	
		String policyName = "LJCG2QLRFEC08Z2DD7ID";
		EndPointResponse theEndpoint = new EndPointResponse();
		
		try{
			PolicyDecision theVerdict = pidAdaptor.makeDecision(theEndpoint, policyName);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
