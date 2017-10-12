package com.idw.policymanager.web;

import org.springframework.beans.factory.annotation.Autowired;
import com.idw.policymanager.enumerator.DecisionType;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import com.idw.policymanager.domain.Policy;
import com.idw.policymanager.domain.PolicyDecision;
import com.idw.policymanager.domain.axn.EndPointResponse;
import com.idw.policymanager.service.PolicyDecisionService;
import com.idw.policymanager.util.PolicyManagerException;
import com.sun.media.jfxmedia.logging.Logger;
/*
 * @author avanderwoude
 */
@Controller
public class PolicyDecisionController {
	
	@Autowired
	private PolicyDecisionService pdService;
	
	@RequestMapping(value = "/")
    public String index() {
		System.out.println("Index.html");
        return "index.html";
    }

	@GetMapping("/test")
	@ResponseBody
	@Transactional(readOnly = true)
	public PolicyDecision helloWorld() {
		
		PolicyDecision theVerdict = new PolicyDecision();
		
		try {
			Policy testPolicy = this.pdService.getPolicyForApiKey("test");
			theVerdict.setMessage("Policy Name test:"+testPolicy.getPolicyName());
			theVerdict.approve();
			
		} catch (PolicyManagerException e) {
			theVerdict.setMessage("Failure: "+e.getErrorMessage());
		}
		return theVerdict;
	}
	
	//TODO: handle error responses and give appropriate pieces
	@PostMapping(value = "/decide/{apikey}")
	@ResponseBody
	@Transactional(readOnly = true)
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