package com.idw.policymanager.adaptor.plainid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.idw.policymanager.adaptor.IPolicyEngineAdaptor;
import com.idw.policymanager.adaptor.PolicyEngineBaseAdaptor;
import com.idw.policymanager.adaptor.plainid.request.PlainIDRequest;
import com.idw.policymanager.adaptor.plainid.response.Access;
import com.idw.policymanager.adaptor.plainid.response.Attributes;
import com.idw.policymanager.adaptor.plainid.response.Constraint;
import com.idw.policymanager.adaptor.plainid.response.PlainIDResponse;
import com.idw.policymanager.domain.PMConfig;
import com.idw.policymanager.domain.PolicyDecision;
import com.idw.policymanager.domain.axn.EndPointResponse;
import com.idw.policymanager.enumerator.DecisionTypeEnum;
import com.idw.policymanager.exception.PolicyManagerException;
import com.iddw.axn.common.s3client.AXNS3ServiceClient;
import com.iddw.axn.common.util.HttpUtil;
import com.iddw.axn.common.util.StringUtil;
/*
 * @author avanderwoude
 */

public class PlainIDAdaptor extends PolicyEngineBaseAdaptor implements IPolicyEngineAdaptor {
	
	String url;
	String clientId;
	String clientSecret;

	@Override
	public void initialize(PMConfig config) throws PolicyManagerException {
		//use baseclass to get props
		this.setupPropsFromS3(config);
		url = this.properties.get("plainid.api.url");
		clientId = this.properties.get("plainid.api.clientId");
		clientSecret = this.properties.get("plainid.api.clientSecret");
	}
	
	@Override
	public PolicyDecision makeDecision(EndPointResponse theEndpoint, String policyName) throws PolicyManagerException {
		PolicyDecision theVerdict = new PolicyDecision();
		
		String mbun = theEndpoint.getMbun();
		//TODO: figure this one out? I thought it was user. I was wrong - is it policyName actualy?
		mbun = "WayneB";
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
		List<String> devicePass = new ArrayList<String>();
		devicePass.add("Fail");
		Map dummyEnvironment = new HashMap<String, List<String>>();
		dummyEnvironment.put("DeviceIDApprove", devicePass);
		theRequestBody.setEnvironment(dummyEnvironment);
		
		//make request
		try {
			responseString = HttpUtil.sendPOSTRequest(url, theRequestBody);
		} catch (Exception e) {
			//convert to proper exception
			throw new PolicyManagerException("An error occurred while trying to send the request: "+e.getMessage());
		}
		
		System.out.println("Response: "+responseString);
		
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
			theResponse = (PlainIDResponse) StringUtil.parseObjectFromJSON(responseString, new PlainIDResponse());
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
			throw new PolicyManagerException("There was a problem parsing the response from PlainID: "+e.getMessage());
		}
		//handle response data 
		//find the 'action' from the json response. Should be approve, deny
		//down the json rabbit hole we go...
		Access theAccessResponse = theResponse.getResponse().get(0).getAccess().get(0);
		String decisionString = theAccessResponse.getActions().get(0).getAction();
		
		//normailize to enum
		DecisionTypeEnum decision = DecisionTypeEnum.valueOf(decisionString);
		
		//look for obligation or additional attributes
		Attributes attributes = theAccessResponse.getAttributes();
		
		String obligationApiKey = null;
		try{
			List<Constraint> constraints = theAccessResponse.getActions().get(0).getConstraints();
			if (!constraints.isEmpty()){
				//there are constraints. Are any of these obligations to stepup?
				for (Constraint c : constraints){
					if (c.getObligations() != null){
						//found constraint.
						obligationApiKey = c.getObligations().get(0).getObligation();
					}
				}
			}
		} catch (Exception e){
			e.printStackTrace();
			throw new PolicyManagerException("There was a problem parsing the response from PlainID: "+e.getMessage());
		}
		
		if (obligationApiKey != null){
			decision = DecisionTypeEnum.obligation;
			theVerdict.setObligationApiKey(obligationApiKey);	
		}
		
		if (decision == null)
			throw new PolicyManagerException("Policy decision of invalid type: "+decisionString);
		
		//build return object
		theVerdict.setConclusion(decision);
		theVerdict.setTheEndpointData(theEndpoint);
		theVerdict.setStatus("success");

		return theVerdict;
	}
}
