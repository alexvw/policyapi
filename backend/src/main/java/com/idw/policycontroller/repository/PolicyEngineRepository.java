package com.idw.policymanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.idw.policymanager.domain.PolicyEngine;

/*
 * @author avanderwoude
 */
public interface PolicyEngineRepository extends CrudRepository<PolicyEngine, Long> {
	/*
	List<User> findByCredential(@Param("credential") String credential);*/
}
