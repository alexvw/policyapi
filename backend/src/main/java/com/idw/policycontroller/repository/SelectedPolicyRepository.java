package com.idw.policymanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.idw.policymanager.domain.SelectedPolicy;

/*
 * @author avanderwoude
 */
public interface SelectedPolicyRepository extends CrudRepository<SelectedPolicy, Long> {
	List<SelectedPolicy> findByApiKey(String apiKey);
}
