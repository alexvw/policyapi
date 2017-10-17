package com.idw.policymanager.adaptor.plainid.request;

import java.util.List;
import java.util.Map;

import lombok.Data;
/*
 * @author avanderwoude
 */

@Data
public class PlainIDRequest {
	private String entityId;
	private String clientId;
	private String clientSecret;
	private Map<String, List<String>> environment;
	
	/*
	 * {
		"entityId": "WayneB",
		"clientId":"LJCG2QLRFEC08Z2DD7ID",
		"clientSecret":"iFc6B6a9g)*zk4tmHdFGXgL7%2QLhwaVmMiF3Wo*",
		"environment": {
		     "DeviceIDApprove": ["Pass"]
		  }
		}
	 */
	
	public PlainIDRequest(String entityId, String clientId, String clientSecret) {
		super();
		this.entityId = entityId;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}
}
