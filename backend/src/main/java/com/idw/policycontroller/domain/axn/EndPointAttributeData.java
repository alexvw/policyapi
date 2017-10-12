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
@JsonPropertyOrder({"attributeType","dateCreated","values"})
public class EndPointAttributeData implements Serializable{
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
	
	
	public void addAttribute(String attrName,String attrValue){
		if(this.values == null){
			this.values = new ArrayList<Map<String,String>>();
		}
		Map<String,String> asrMap = new HashMap<String,String>();
		asrMap.put(attrName, attrValue);
		this.values.add(asrMap);
	}
}
