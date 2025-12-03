package org.khanguhizi.insurancedetails.service;

import lombok.*;
import org.khanguhizi.insurancedetails.dto.*;
import org.khanguhizi.insurancedetails.exceptions.*;
import org.khanguhizi.insurancedetails.models.*;
import org.khanguhizi.insurancedetails.repository.*;
import org.khanguhizi.insurancedetails.utilities.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.*;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserManagementService {
    private final UserManagementRepository userManagementRepository;
    private final PasswordEncoder passwordEncoder;

    public ApiResponse register(RegisterRequest registerRequest) {
        Optional<UserDetails> emailCheck = userManagementRepository.findByEmail(registerRequest.getEmail());
        if (emailCheck.isPresent()) {
            throw new DuplicateCredentialsException("User with email " + registerRequest.getEmail() + " already exists!");
        }

        Optional<UserDetails> phoneNumberCheck = userManagementRepository.findByPhoneNumber(registerRequest.getPhoneNumber());
        if (phoneNumberCheck.isPresent()) {
            throw new DuplicateCredentialsException("User with phone number " + registerRequest.getPhoneNumber() + " already exists!");
        }

        Optional<UserDetails> nationalIdCheck = userManagementRepository.findByNationalId(registerRequest.getNationalId());
        if (nationalIdCheck.isPresent()) {
            throw new DuplicateCredentialsException("User with national ID " + registerRequest.getNationalId() + " already exists!");
        }

        Optional<UserDetails> usernameCheck = userManagementRepository.findByUsername(registerRequest.getUsername());
        if (usernameCheck.isPresent()) {
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
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        userManagementRepository.save(userDetails);

        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setUsername(userDetails.getUsername());
        registerResponse.setEmail(userDetails.getEmail());
        registerResponse.setFirstName(userDetails.getFirstName());
        registerResponse.setLastName(userDetails.getLastName());
        registerResponse.setPhoneNumber(userDetails.getPhoneNumber());
        registerResponse.setNationalId(userDetails.getNationalId());
        registerResponse.setDateOfBirth(userDetails.getDateOfBirth());

        return ApiResponse.builder()
                .message("User Registration Successful!")
                .data(registerResponse)
                .status(String.valueOf(HttpStatus.CREATED.value()))
                .build();
    }

    public ApiResponse login(LoginRequest loginRequest) {
        Optional<UserDetails> usernameOrEmailCheck = userManagementRepository.findByUsernameOrEmail(loginRequest.getUsernameOrEmail());
        if (usernameOrEmailCheck.isEmpty()) {
            throw new RuntimeException("No user found with username or email " + loginRequest.getUsernameOrEmail());
        }
        UserDetails userDetails = usernameOrEmailCheck.get();


        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserId(userDetails.getUserId());
        loginResponse.setUsername(userDetails.getUsername());
        loginResponse.setEmail(userDetails.getEmail());
        loginResponse.setFirstName(userDetails.getFirstName());
        loginResponse.setLastName(userDetails.getLastName());
        loginResponse.setNationalId(userDetails.getNationalId());
        loginResponse.setPhoneNumber(userDetails.getPhoneNumber());
        loginResponse.setDateOfBirth(userDetails.getDateOfBirth());
        //loginResponse.setToken("dummy-jwt-token");


        if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password!");
        } else {
            return ApiResponse.builder()
                    .message("Login Successful!")
                    .data(loginResponse)
                    .status(String.valueOf(HttpStatus.OK.value()))
                    .build();
        }
    }
}
