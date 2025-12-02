package org.khanguhizi.insurancedetails.repository;

import org.khanguhizi.insurancedetails.models.PolicyDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PolicyManagementRepository extends JpaRepository<PolicyDetails, Long> {
    Optional<PolicyDetails> findByInsurerPolicyNumber(String insurerPolicyNumber);
}
