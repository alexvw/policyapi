package com.idw.policycontroller.domain.axn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"status","errorCode","errorDescription","idpType","credential","mbun","maxToken","dateCreated","credentialCreationDate","userAttributes","acquiredAttributes","preferenceAttributes","userAssertionList"})
public class EndPointResponse implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4029191517323339476L;
	private String status="success";
	private String errorCode="";
	private String errorDescription="";
	
	private String idpType;
	private String credential;
	private String mbun;
	private String maxToken;
	private String dateCreated;
	private String credentialCreationDate;
	private List<EndPointAttributeData> userAttributes;
	private List<EndPointAttributeData> acquiredAttributes;
	private List<EndPointAttributeData> preferenceAttributes;
	private List<EndPointAssertionData> userAssertionList;
	
	
	
	public EndPointResponse() {
		super();
	}
	public String getCredential() {
		return credential;
	}
	public void setCredential(String credential) {
		this.credential = credential;
	}
	public String getIdpType() {
		return idpType;
	}
	public void setIdpType(String idpType) {
		this.idpType = idpType;
		
	}
		
	public String getCredentialCreationDate() {
		return credentialCreationDate;
	}
	public void setCredentialCreationDate(String credentialCreationDate) {
		this.credentialCreationDate = credentialCreationDate;
	}
	public List<EndPointAttributeData> getUserAttributes() {
		return userAttributes;
	}
	public void setUserAttributes(List<EndPointAttributeData> userAttributes) {
		this.userAttributes = userAttributes;
	}
	public List<EndPointAssertionData> getUserAssertionList() {
		return userAssertionList;
	}
	public void setUserAssertionList(List<EndPointAssertionData> userAssertionList) {
		this.userAssertionList = userAssertionList;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getMaxToken() {
		return maxToken;
	}
	public void setMaxToken(String maxToken) {
		this.maxToken = maxToken;
	}
	public String getMbun() {
		return mbun;
	}
	public void setMbun(String mbun) {
		this.mbun = mbun;
	}
	public List<EndPointAttributeData> getAcquiredAttributes() {
		return acquiredAttributes;
	}
	public void setAcquiredAttributes(List<EndPointAttributeData> acquiredAttributes) {
		this.acquiredAttributes = acquiredAttributes;
		
	}
	public List<EndPointAttributeData> getPreferenceAttributes() {
		return preferenceAttributes;
	}
	public void setPreferenceAttributes(
			List<EndPointAttributeData> preferenceAttributes) {
		this.preferenceAttributes = preferenceAttributes;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
