package com.idw.policymanager.domain;

import com.idw.policymanager.enumerator.DecisionType;
import com.idw.policymanager.domain.axn.EndPointResponse;

import lombok.Data;
/*
 * @author avanderwoude
 */

@Data
public class PolicyDecision {
	
	private DecisionType conclusion;
	private String obligationApiKey;
	private EndPointResponse theEndpointData;
	private String status;
	private String message;
	
	public PolicyDecision(String message) {
		super();
		this.status = "fail";
		this.message = message;
	}

	public PolicyDecision() {
		// TODO Auto-generated constructor stub
	}
	
	public void approve(){
		this.conclusion = DecisionType.approve;
	}
	public void deny(){
		this.conclusion = DecisionType.deny;
	}
	public void obligation(String obligationApiKey){
		this.conclusion = DecisionType.obligation;
		this.obligationApiKey = obligationApiKey;
	}
}
