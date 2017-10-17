package com.idw.policymanager.domain;

import java.util.Date;

import javax.persistence.*;

import com.idw.policymanager.enumerator.DecisionTypeEnum;

import lombok.Data;

/*
 * @author avanderwoude
 * Log object to keep track of decisions made
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

	public DecisionLog() {
		super();
	}
	
	public DecisionLog(Date date, SelectedPolicy selectedPolicy, String decision, String data) {
		super();
		this.date = date;
		this.selectedPolicy = selectedPolicy;
		this.decision = decision;
		this.data = data;
	}
}