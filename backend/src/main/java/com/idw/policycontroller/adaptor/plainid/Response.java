
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
"contextData",
"access"
})
public class Response {

@JsonProperty("contextData")
private Object contextData;
@JsonProperty("access")
private List<Access> access = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("contextData")
public Object getContextData() {
return contextData;
}

@JsonProperty("contextData")
public void setContextData(Object contextData) {
this.contextData = contextData;
}

@JsonProperty("access")
public List<Access> getAccess() {
return access;
}

@JsonProperty("access")
public void setAccess(List<Access> access) {
this.access = access;
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