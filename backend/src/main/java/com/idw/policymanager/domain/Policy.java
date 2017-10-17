package com.idw.policymanager.domain;

import javax.persistence.*;
import lombok.Data;

/*
 * @author avanderwoude
 * a single policy. attached to a policy engine, and likely contains a set of rules to run against an endpoint
 */

@Data
@Entity
public class Policy {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
    @JoinColumn(name = "PolicyEngine_ID")
	private PolicyEngine policyEngine;
	private String policyName;
	
	public Policy(){}

	//International Affairs?
	public Policy(Long id, PolicyEngine policyEngine, String policyName) {
		super();
		this.id = id;
		this.policyEngine = policyEngine;
		this.policyName = policyName;
	}
}