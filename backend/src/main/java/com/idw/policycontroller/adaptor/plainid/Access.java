
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
"path",
"actions",
"attributes"
})
public class Access {
	
	@JsonProperty("path")
	private String path;
	@JsonProperty("actions")
	private List<Action> actions = null;
	@JsonProperty("attributes")
	private Attributes attributes;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	@JsonProperty("path")
	public String getPath() {
		return path;
	}
	
	@JsonProperty("path")
	public void setPath(String path) {
		this.path = path;
	}
	
	@JsonProperty("actions")
	public List<Action> getActions() {
		return actions;
	}
	
	@JsonProperty("actions")
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
	
	@JsonProperty("attributes")
	public Attributes getAttributes() {
		return attributes;
	}
	
	@JsonProperty("attributes")
	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
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