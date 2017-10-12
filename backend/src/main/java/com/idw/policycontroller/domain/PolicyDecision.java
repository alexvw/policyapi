package com.idw.policycontroller.domain;

import com.idw.policycontroller.enumerator.DecisionType;
import com.idw.policycontroller.domain.axn.EndPointResponse;

import lombok.Data;

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
	
	public void permit(){
		this.conclusion = DecisionType.permit;
	}
	public void deny(){
		this.conclusion = DecisionType.deny;
	}
	public void obligation(String obligationApiKey){
		this.conclusion = DecisionType.obligation;
		this.obligationApiKey = obligationApiKey;
	}
}
