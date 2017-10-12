package com.idw.policycontroller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.idw.policycontroller.domain.Policy;

/*
 * @author avanderwoude
 */
public interface PolicyRepository extends CrudRepository<Policy, Long> {
	List<Policy> findByPolicyName(String policyName);
	//findbypolicynameandpolicyengine
}
