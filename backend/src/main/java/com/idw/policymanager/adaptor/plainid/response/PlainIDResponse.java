package com.idw.policymanager.adaptor.plainid.response;

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
"tokenValidity",
"response"
})
public class PlainIDResponse {
	
	/*
	 * {
	    "tokenValidity": 0,
	    "response": [
	        {
	            "contextData": null,
	            "access": [
	                {
	                    "path": "//operation/authenticate",
	                    "actions": [
	                        {
	                            "action": "approve",
	                            "constraints": []
	                        }
	                    ],
	                    "attributes": {
	                        "Name": [
	                            "Authentication"
	                        ]
	                    }
	                }
	            ]
	        }
	    ]
	}
	 */

	@JsonProperty("tokenValidity")
	private Integer tokenValidity;
	@JsonProperty("response")
	private List<Response> response = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	@JsonProperty("tokenValidity")
	public Integer getTokenValidity() {
		return tokenValidity;
	}
	
	@JsonProperty("tokenValidity")
	public void setTokenValidity(Integer tokenValidity) {
		this.tokenValidity = tokenValidity;
	}
	
	@JsonProperty("response")
	public List<Response> getResponse() {
		return response;
	}
	
	@JsonProperty("response")
	public void setResponse(List<Response> response) {
		this.response = response;
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