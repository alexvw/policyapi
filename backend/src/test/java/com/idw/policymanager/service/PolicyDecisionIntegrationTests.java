package com.idw.policymanager.service;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.idw.policymanager.domain.Policy;
import com.idw.policymanager.domain.PolicyDecision;
import com.idw.policymanager.domain.PolicyEngine;
import com.idw.policymanager.domain.SelectedPolicy;
import com.idw.policymanager.enumerator.DecisionTypeEnum;
import com.idw.policymanager.exception.PolicyManagerException;
import com.idw.policymanager.repository.PolicyEngineRepository;
import com.idw.policymanager.repository.PolicyRepository;
import com.idw.policymanager.repository.SelectedPolicyRepository;

import com.idw.policymanager.util.PolicyManagerUtil;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

/**
 * @author avanderwoude
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PolicyDecisionIntegrationTests {
	

	@Autowired
	private PolicyRepository policyRepository;
	
	@Autowired
	private SelectedPolicyRepository spRepository;
	
	@Autowired
	private PolicyEngineRepository peRepository;
	
	@Autowired
	private PolicyDecisionService pdService;
	
	
	@Test
	public void findsAtLeastOnePolicyEngine() {
		List<PolicyEngine> pEngines = (List<PolicyEngine>) peRepository.findAll();
		assertThat(!pEngines.isEmpty());
	}
	
	@Test
	public void findsAtLeastOnePolicy() {
		List<Policy> policies = (List<Policy>) policyRepository.findAll();
		assertThat(!policies.isEmpty());
	}
	
	@Test
	public void findsAtLeastOneSelectedPolicy() {
		List<SelectedPolicy> sPolicies = (List<SelectedPolicy>) spRepository.findAll();
		assertThat(!sPolicies.isEmpty());
	}
	
	@Test
	public void testDummyPolicy() throws PolicyManagerException {
		Policy testApproval = pdService.getPolicyForApiKey("testApprove");
		PolicyEngine dummyPE = pdService.getPolicyEngineForPolicy(testApproval.getId());
		assertThat(dummyPE.getDescription().equals("Dummy Adaptor for Unit Tests"));
		PolicyDecision testDecision = pdService.getDecisionForEndpoint(PolicyManagerUtil.createDummyEndpoint(), "testApprove");
		assertThat(testDecision.getConclusion() == DecisionTypeEnum.approve);
	}
}