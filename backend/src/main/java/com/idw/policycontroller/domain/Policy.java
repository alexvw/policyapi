package com.idw.policymanager.domain;

import javax.persistence.*;
import lombok.Data;

/*
 * @author avanderwoude
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