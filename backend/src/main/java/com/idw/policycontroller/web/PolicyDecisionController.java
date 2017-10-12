package com.idw.policycontroller.web;

import org.springframework.beans.factory.annotation.Autowired;
import com.idw.policycontroller.enumerator.DecisionType;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import com.idw.policycontroller.domain.Policy;
import com.idw.policycontroller.domain.PolicyDecision;
import com.idw.policycontroller.domain.axn.EndPointResponse;
import com.idw.policycontroller.service.PolicyDecisionService;
import com.idw.policycontroller.util.PolicyControllerException;
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
			theVerdict.permit();
			
		} catch (PolicyControllerException e) {
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
		} catch (PolicyControllerException e) {
			e.printStackTrace();
			//TODO: handle errors and returning data
			theVerdict = new PolicyDecision(e.getErrorMessage());
		}
		System.out.println("Policy Decision: "+theVerdict.getConclusion());
		
		return theVerdict;
	}
}