package com.idw.policymanager.adaptor.plainid.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

/*
 * @author avanderwoude
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"obligation"
})
@Data
public class Obligation {

@JsonProperty("obligation")
public String obligation;

}