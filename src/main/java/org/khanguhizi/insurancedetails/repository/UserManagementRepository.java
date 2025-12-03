package org.khanguhizi.insurancedetails.repository;

import org.khanguhizi.insurancedetails.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserManagementRepository extends JpaRepository<UserDetails, Long> {
    Optional<UserDetails> findByUsernameOrEmail(String usernameOrEmail);
    Optional<UserDetails> findByUsername(String username);
    Optional<UserDetails> findByEmail(String email);
    Optional<UserDetails> findByUserId(Long userId);
    Optional<UserDetails> findByNationalId(String nationalId);
    Optional<UserDetails> findByPhoneNumber(String phoneNumber);
}
