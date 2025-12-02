/*
package org.khanguhizi.insurancedetails.service;

import lombok.*;
import org.khanguhizi.insurancedetails.dto.*;
import org.khanguhizi.insurancedetails.exceptions.DuplicateCredentialsException;
import org.khanguhizi.insurancedetails.models.*;
import org.khanguhizi.insurancedetails.repository.*;
import org.khanguhizi.insurancedetails.utilities.*;
import org.springframework.stereotype.*;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserManagementService {
    private final UserManagementRepository userManagementRepository;

    public ApiResponse register(RegisterRequest registerRequest) {
        Optional<UserDetails> emailCheck = userManagementRepository.findByEmail(registerRequest.getEmail());
        if(emailCheck.isPresent()){
            throw new DuplicateCredentialsException("User with email " + registerRequest.getEmail() + " already exists!");
        }

        Optional<UserDetails> phoneNumberCheck = userManagementRepository.findByPhoneNumber(registerRequest.getPhoneNumber());
        if(phoneNumberCheck.isPresent()){
            throw new DuplicateCredentialsException("User with phone number " + registerRequest.getPhoneNumber() + " already exists!");
        }

        Optional<UserDetails> nationalIdCheck = userManagementRepository.findByNationalId(registerRequest.getNationalId());
        if(nationalIdCheck.isPresent()){
            throw new DuplicateCredentialsException("User with national ID " + registerRequest.getNationalId() + " already exists!");
        }

        Optional<UserDetails> usernameCheck = userManagementRepository.findByUsername(registerRequest.getUsername());
        if(usernameCheck.isPresent()){
            throw new DuplicateCredentialsException("User with username " + registerRequest.getUsername() + " already exists!");
        }

        if (!PasswordValidator.isStrongPassword(registerRequest.getPassword())) {
            throw new RuntimeException("Weak password! Must be at least 8 characters long, contain uppercase, lowercase, number, and special character.");
        }

        UserDetails userDetails = UserDetails.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .phoneNumber(registerRequest.getPhoneNumber())
                .nationalId(registerRequest.getNationalId())
                //.password(PasswordEncoder.encodePassword(registerRequest.getPassword()))
                .build();
    }
}

 */
