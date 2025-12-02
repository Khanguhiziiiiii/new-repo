package org.khanguhizi.insurancedetails.repository;

import org.khanguhizi.insurancedetails.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LimitsOfLiabilityMasterTableRepository extends JpaRepository<LimitsOfLiabilityMasterTable, Long> {
    Optional<LimitsOfLiabilityMasterTable> findByDescription(String description);

}
