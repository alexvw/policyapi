package com.idw.policymanager.adaptor.plainid.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

/*
 * @author avanderwoude
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"loginParamsConstraints",
"obligations"
})
@Data
public class Constraint {

@JsonProperty("loginParamsConstraints")
public List<Object> loginParamsConstraints;
@JsonProperty("obligations")
public List<Obligation> obligations;

}