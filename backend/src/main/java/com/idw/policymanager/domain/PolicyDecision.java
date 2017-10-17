package com.idw.policymanager.domain;

import com.idw.policymanager.enumerator.DecisionTypeEnum;
import com.idw.policymanager.domain.axn.EndPointResponse;

import lombok.Data;
/*
 * @author avanderwoude
 * A return type to encapsulate a final decision from a policy engine.
 */

@Data
public class PolicyDecision {
	
	//the conclusion that the policyengine came to
	private DecisionTypeEnum conclusion;
	//if the conclusion was obligation, this is the apikey to redirect to next to fulfull that obligation
	private String obligationApiKey;
	//endpoint data this policy decision was made for
	private EndPointResponse theEndpointData;
	//status of the request. should be success or fail
	private String status;
	//if something went wrong, what happened
	private String message;
	
	public PolicyDecision(String message) {
		super();
		this.status = "fail";
		this.message = message;
	}

	public PolicyDecision() {
	}
	
	public void approve(){
		this.conclusion = DecisionTypeEnum.approve;
	}
	public void deny(){
		this.conclusion = DecisionTypeEnum.deny;
	}
	public void obligation(String obligationApiKey){
		this.conclusion = DecisionTypeEnum.obligation;
		this.obligationApiKey = obligationApiKey;
	}
}
