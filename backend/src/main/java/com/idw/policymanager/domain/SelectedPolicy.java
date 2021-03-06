package com.idw.policymanager.domain;

import javax.persistence.*;
import lombok.Data;

/*
 * @author avanderwoude
 * A single selected policy for an apikey. this allows us to know which policies to apply and via what pEngine
 */

@Data
@Entity
public class SelectedPolicy {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String apiKey;
	@ManyToOne
    @JoinColumn(name = "Policy_ID")
	private Policy policy;
	
	public SelectedPolicy(){}

	public SelectedPolicy(Long id, String apiKey, Policy policy) {
		super();
		this.id = id;
		this.apiKey = apiKey;
		this.policy = policy;
	}
}