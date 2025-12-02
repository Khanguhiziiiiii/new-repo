package org.khanguhizi.insurancedetails.repository;

import org.khanguhizi.insurancedetails.models.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientManagementRepository extends JpaRepository<ClientDetails, Long> {
    Optional<ClientDetails> findByClientId(Long clientId);

    Optional<ClientDetails> findByIdNo(String idNo);

    Optional<ClientDetails> findByEmail(String email);

    Optional<ClientDetails> findByKraPin(String kraPin);
}
