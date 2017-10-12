/**
 * 
 */
package com.idw.policycontroller.domain.axn;

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
@JsonPropertyOrder({"provider","serviceOffering","attributeType","dateCreated","values"})
public class EndPointAcquiredAttributeData implements Serializable{
	private String provider;
	private String serviceOffering;
	private String attributeType;
	private List<Map<String,String>> values;
	private String dateCreated;
	public String getAttributeType() {
		return attributeType;
	}
	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}
	public List<Map<String, String>> getValues() {
		return values;
	}
	public void setValues(List<Map<String, String>> values) {
		this.values = values;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
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
	public void addAcquiredAttribute(String attrName,String attrValue){
		if(this.values == null){
			this.values = new ArrayList<Map<String,String>>();
		}
		Map<String,String> asrMap = new HashMap<String,String>();
		asrMap.put(attrName, attrValue);
		this.values.add(asrMap);
	}
}
