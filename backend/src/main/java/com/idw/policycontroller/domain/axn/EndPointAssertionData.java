/**
 * 
 */
package com.idw.policymanager.domain.axn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author girish.bhakta
 *
 */
@JsonPropertyOrder({"provider","serviceOffering","dateAsserted","assertions"})
public class EndPointAssertionData implements Serializable{

	private String provider;
	private String serviceOffering;
	private String dateAsserted;
	private List<Map<String,String>> assertions;
	
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getServiceOffering() {
		return serviceOffering;
	}
	public void setServiceOffering(String serviceOffering) {
		this.serviceOffering = serviceOffering;
	}
	
	
	
	public List<Map<String, String>> getAssertions() {
		return assertions;
	}
	public void setAssertions(List<Map<String, String>> assertions) {
		this.assertions = assertions;
	}
	public String getDateAsserted() {
		return dateAsserted;
	}
	public void setDateAsserted(String dateAsserted) {
		this.dateAsserted = dateAsserted;
	}
	
	public void addAssertion(String asrName,String assertedValue){
		if(this.assertions == null){
			this.assertions = new ArrayList<Map<String,String>>();
		}
		Map<String,String> asrMap = new HashMap<String,String>();
		asrMap.put(asrName, assertedValue);
		this.assertions.add(asrMap);
	}
}
