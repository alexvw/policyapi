
package com.idw.policymanager.adaptor.plainid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/*
 * @author avanderwoude
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"action",
"constraints"
})
public class Action {
	
	@JsonProperty("action")
	private String action;
	@JsonProperty("constraints")
	private List<Object> constraints = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	@JsonProperty("action")
	public String getAction() {
		return action;
	}
	
	@JsonProperty("action")
	public void setAction(String action) {
		this.action = action;
	}
	
	@JsonProperty("constraints")
	public List<Object> getConstraints() {
		return constraints;
	}
	
	@JsonProperty("constraints")
	public void setConstraints(List<Object> constraints) {
		this.constraints = constraints;
	}
	
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}
	
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}