package com.idw.policymanager.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.idw.policymanager.adaptor.IPolicyEngineAdaptor;
import com.idw.policymanager.adaptor.PolicyEngineAdaptorFactory;
import com.idw.policymanager.domain.Policy;
import com.idw.policymanager.domain.PolicyDecision;
import com.idw.policymanager.domain.PolicyEngine;
import com.idw.policymanager.domain.SelectedPolicy;
import com.idw.policymanager.domain.axn.EndPointResponse;
import com.idw.policymanager.repository.PolicyEngineRepository;
import com.idw.policymanager.repository.PolicyRepository;
import com.idw.policymanager.repository.SelectedPolicyRepository;
import com.idw.policymanager.util.PolicyManagerException;

/*
 * @author avanderwoude
 */

@Component("policyDecisionService")
@Transactional
public class PolicyDecisionServiceImpl implements PolicyDecisionService {
	private final PolicyRepository policyRepository;
	private final SelectedPolicyRepository spRespository;
	private final PolicyEngineRepository peRepository;

	public PolicyDecisionServiceImpl(PolicyRepository policyRepository, SelectedPolicyRepository spRespository,
			PolicyEngineRepository peRepository) {
		super();
		this.policyRepository = policyRepository;
		this.spRespository = spRespository;
		this.peRepository = peRepository;
	}

	/*
	 * (non-Javadoc)
	 * @see com.idw.policymanager.service.PolicyDecisionService#getDecisionForEndpoint(com.idw.policymanager.domain.axn.EndPointResponse, java.lang.String)
	 */
	@Override
	public PolicyDecision getDecisionForEndpoint(EndPointResponse theEndpoint, String apiKey) throws PolicyManagerException {
		Assert.notNull(theEndpoint, "Endpoint Data must no be null");
		Assert.hasText(apiKey, "must pass an apikey");
		
		//get policy for this apikey
		Policy thePolicy = this.getPolicyForApiKey(apiKey);
		String policyName = thePolicy.getPolicyName();
		System.out.println(thePolicy.getId()+" / "+thePolicy.getPolicyName());
		
		if (policyName == null || policyName.isEmpty())
			throw new PolicyManagerException("Invalid PolicyName for policy: "+thePolicy.getId());
		
		//find policyengine for policy
		PolicyEngine pEngineToUse = thePolicy.getPolicyEngine();
		
		//manufacture adaptor for policyengine
		IPolicyEngineAdaptor pEngine;
		System.out.println("Reflecting class: "+pEngineToUse.getAdaptorClass());
		try {
			//attempt to find adaptor class and instantiate
			pEngine = PolicyEngineAdaptorFactory.getPolicyEngineAdaptor(pEngineToUse.getAdaptorClass());
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			throw new PolicyManagerException(e.getMessage(), e.getMessage()+"FATAL: There was a problem building the PolicyEngineAdaptor");
		}
		
		PolicyDecision theVerdict = pEngine.makeDecision(theEndpoint, policyName);
		
		if (theVerdict == null)
			throw new PolicyManagerException("The adaptor returned a null decision");
		
		return theVerdict;
	}

	/*
	 * (non-Javadoc)
	 * @see com.idw.policymanager.service.PolicyDecisionService#getPolicyForApiKey(java.lang.String)
	 */
	@Override
	public Policy getPolicyForApiKey(String apiKey) throws PolicyManagerException {
		Assert.notNull(apiKey, "ApiKey must not be null");
		//find the mapping
		List<SelectedPolicy> selectedPolicies = this.spRespository.findByApiKey(apiKey);
		if (selectedPolicies == null || selectedPolicies.size() < 1)
			throw new PolicyManagerException("No Policies found for apikey: "+apiKey);
		else if (selectedPolicies.size() > 1)
			throw new PolicyManagerException("Multiple Policies configured for apikey: "+apiKey);
		//if theres just one, grab its id
		Long policyId = selectedPolicies.get(0).getId();
		//find the policy itself
		Policy thePolicy = policyRepository.findOne(policyId);
		//should exist
		if (thePolicy == null)
			throw new PolicyManagerException("Invalid Policy Configured: " + policyId);
		//return single found policy entity
		else return thePolicy;
	}

	/*
	 * (non-Javadoc)
	 * @see com.idw.policymanager.service.PolicyDecisionService#getPolicyEngineForPolicy(java.lang.Long)
	 */
	@Override
	public PolicyEngine getPolicyEngineForPolicy(Long peid) throws PolicyManagerException {
		Assert.notNull(peid, "policyId must not be null");
		//find the pe
		PolicyEngine thePE = this.peRepository.findOne(peid);
		if (thePE == null)
			throw new PolicyManagerException("Invalid PolicyEngineId: "+peid);
		else return thePE;
	}
}


