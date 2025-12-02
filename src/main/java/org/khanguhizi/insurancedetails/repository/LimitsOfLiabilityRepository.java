package org.khanguhizi.insurancedetails.repository;

import org.khanguhizi.insurancedetails.models.LimitsOfLiability;
import org.khanguhizi.insurancedetails.models.LimitsOfLiabilityMasterTable;
import org.khanguhizi.insurancedetails.models.PolicyDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LimitsOfLiabilityRepository extends JpaRepository<LimitsOfLiability, Long> {
    Optional<LimitsOfLiability> findByPolicyNumberAndDescription(PolicyDetails policyNumber,  LimitsOfLiabilityMasterTable description);
    List<LimitsOfLiability> findByPolicyNumber(String policyNumber);
}
