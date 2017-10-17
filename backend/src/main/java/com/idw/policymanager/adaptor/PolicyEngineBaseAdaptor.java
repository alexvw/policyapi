package com.idw.policymanager.adaptor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.iddw.axn.common.s3client.AXNS3ServiceClient;
import com.iddw.axn.common.util.Util;
import com.idw.policymanager.domain.PMConfig;
import com.idw.policymanager.exception.PolicyManagerException;

/*
 * @author avanderwoude
 */

public class PolicyEngineBaseAdaptor{
	
	//Moved functions to common util classes in axn
	//may still have common policynamager adaptor functions here, though, so I'm leaving it
	
	//config properties injected from s3 at initialization
	public Map<String, String> properties;
	
	public void setupPropsFromS3(PMConfig config) throws PolicyManagerException{
		try {
			InputStream propsStream = AXNS3ServiceClient.readPropertiesAsInputStream(config.getPlainIdFile(), config.getBucket());
			properties = Util.readPropertiesFromInputStream(propsStream);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PolicyManagerException("There was a problem accessing the base s3 config: "+e.getMessage());
		}
	}
}
