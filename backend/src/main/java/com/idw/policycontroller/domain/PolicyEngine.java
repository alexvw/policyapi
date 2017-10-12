package com.idw.policymanager.domain;

import javax.persistence.*;
import lombok.Data;

/*
 * @author avanderwoude
 */

@Data
@Entity
public class PolicyEngine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String adaptorClass;
	private String description;
	
}