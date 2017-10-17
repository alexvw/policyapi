package com.idw.policymanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.idw.policymanager.domain.Policy;

/*
 * @author avanderwoude
 */
public interface PolicyRepository extends CrudRepository<Policy, Long> {
	List<Policy> findByPolicyName(String policyName);
	//findbypolicynameandpolicyenginesearchforid
}
