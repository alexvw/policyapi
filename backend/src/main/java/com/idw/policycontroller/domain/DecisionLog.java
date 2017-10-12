package com.idw.policycontroller.domain;

import java.util.Date;

import javax.persistence.*;
import lombok.Data;

/*
 * @author avanderwoude
 */

@Data
@Entity
public class DecisionLog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private java.util.Date date;
	@ManyToOne
    @JoinColumn(name = "SelectedPolicy_ID")
	private SelectedPolicy selectedPolicy;
	private String decision;
	private String data;
	
}