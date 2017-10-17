package com.idw.policymanager.web;

import org.springframework.beans.factory.annotation.Autowired;
import com.idw.policymanager.enumerator.DecisionTypeEnum;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import com.idw.policymanager.domain.PMConfig;
import com.idw.policymanager.domain.Policy;
import com.idw.policymanager.domain.PolicyDecision;
import com.idw.policymanager.domain.axn.EndPointResponse;
import com.idw.policymanager.service.PolicyDecisionService;
import com.idw.policymanager.util.PolicyManagerUtil;
import com.idw.policymanager.exception.PolicyManagerException;
import com.sun.media.jfxmedia.logging.Logger;
/*
 * @author avanderwoude
 */
@Controller
public class PolicyDecisionController {

	@Autowired
	private PolicyDecisionService pdService;

	/*
	 * Will always return an approve decision
	 */
	@GetMapping("/test")
	@ResponseBody
	@Transactional(readOnly = false)
	public PolicyDecision helloWorld() {
		
		PolicyDecision theVerdict;
		
		try {
			theVerdict = pdService.getDecisionForEndpoint(PolicyManagerUtil.createDummyEndpoint(), "testApprove");
		} catch (PolicyManagerException e) {
			e.printStackTrace();
			//TODO: handle errors and returning data
			theVerdict = new PolicyDecision(e.getErrorMessage());
		}
		return theVerdict;
	}
	
	/*
	 * will take in an apikey and an endpoint json
	 * set up and pass to appropriate pEngine
	 * return PolicyDecision json
	 */
	@PostMapping(value = "/decide/{apikey}")
	@ResponseBody
	@Transactional(readOnly = false)
	public PolicyDecision makeDecision(@PathVariable String apikey, @RequestBody EndPointResponse theEndpoint) {
		Assert.hasText(apikey, "Must pass apikey");
		
		System.out.println("About to make decision for "+ apikey);
		PolicyDecision theVerdict;
		try {
			theVerdict = pdService.getDecisionForEndpoint(theEndpoint, apikey);
		} catch (PolicyManagerException e) {
			e.printStackTrace();
			//TODO: handle errors and returning data
			theVerdict = new PolicyDecision(e.getErrorMessage());
		}
		System.out.println("Policy Decision: "+theVerdict.getConclusion());
		
		return theVerdict;
	}
}