package com.idw.policymanager.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.idw.policymanager.domain.DecisionLog;

/*
 * @author avanderwoude
 */
public interface DecisionLogRepository extends PagingAndSortingRepository<DecisionLog, Long> {
	Page<DecisionLog> findAll(Pageable pageable);
	Page<DecisionLog> findByDecisionContainingAllIgnoringCase(@Param("decision") String decision, Pageable pageable);
}
