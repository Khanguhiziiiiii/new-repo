package org.khanguhizi.insurancedetails.repository;

import org.khanguhizi.insurancedetails.models.ApplicableClauses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicableClausesRepository extends JpaRepository<ApplicableClauses, Long> {
    boolean existsByClause(String clause);
    Optional<ApplicableClauses> findByClauseId(Long clauseId);
    Optional<ApplicableClauses> findByClause(String clause);
}
