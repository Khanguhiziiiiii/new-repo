package org.khanguhizi.insurancedetails.repository;

import org.khanguhizi.insurancedetails.models.MotorVehicleDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MotorVehicleManagementRepository extends JpaRepository <MotorVehicleDetails, Long> {
 Optional<MotorVehicleDetails> findById(Long id);
 Optional<MotorVehicleDetails> findByVehicleRegistration(String vehicleRegistration);
 List<MotorVehicleDetails> findByClientId(Long clientId);
 List<MotorVehicleDetails> findByMotorVehicleId(Long motorVehicleId, Pageable pageable);
}