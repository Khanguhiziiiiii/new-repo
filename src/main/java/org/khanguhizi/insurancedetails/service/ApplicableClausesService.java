package org.khanguhizi.insurancedetails.service;

import lombok.*;
import org.khanguhizi.insurancedetails.dto.*;
import org.khanguhizi.insurancedetails.exceptions.*;
import org.khanguhizi.insurancedetails.models.*;
import org.khanguhizi.insurancedetails.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicableClausesService {
    private ApplicableClausesRepository applicableClausesRepository;

    public ApiResponse createApplicableClause(ApplicableClausesRequest createApplicableClausesRequest) {
        Optional<ApplicableClauses> existingClause = applicableClausesRepository.findByClause(createApplicableClausesRequest.getClause());
        if(existingClause.isPresent()) {
            throw new DuplicateCredentialsException("Clause already exists");
        }

        ApplicableClauses applicableClauses =ApplicableClauses.builder()
                .clause(createApplicableClausesRequest.getClause())
                .build();
        applicableClausesRepository.save(applicableClauses);

        ApplicableClausesResponse createApplicableClausesResponse = new ApplicableClausesResponse();
        createApplicableClausesResponse.setClause(applicableClauses.getClause());

        return ApiResponse.builder()
                .message("Clause created successfully")
                .data(createApplicableClausesResponse)
                .status(String.valueOf(HttpStatus.CREATED.value()))
                .build();
    }

    public ApiResponse fetchAllApplicableClauses(){
        List<ApplicableClauses> applicableClausesList = applicableClausesRepository.findAll();

        if (applicableClausesList.isEmpty()) {
            throw new NoRecordsFoundException("No clauses found");
        }

        List<ApplicableClausesResponse> allApplicableClauses = applicableClausesList.stream()
                .map(applicableClauses -> {
                    ApplicableClausesResponse fetchApplicableClausesResponse = new ApplicableClausesResponse();
                    fetchApplicableClausesResponse.setClause(applicableClauses.getClause());
                    return fetchApplicableClausesResponse;
                })
                .toList();
        return ApiResponse.builder()
                .message("Applicable clauses fetched successfully")
                .data(allApplicableClauses)
                .status(String.valueOf(HttpStatus.OK.value()))
                .build();
    }

    public ApiResponse deleteApplicableClause(Long clauseId) {
        Optional<ApplicableClauses> existingClause = applicableClausesRepository.findByClauseId(clauseId);
        if(existingClause.isEmpty()) {
            throw new NoRecordsFoundException("No clause found");
        }
        applicableClausesRepository.delete(existingClause.get());

        return ApiResponse.builder()
                .message("Clause deleted successfully")
                .status(String.valueOf(HttpStatus.OK.value()))
                .build();
    }

    public ApiResponse updateApplicableClause(ApplicableClausesRequest updateApplicableClausesRequest) {
        Optional<ApplicableClauses> existingClause = applicableClausesRepository.findByClauseId(updateApplicableClausesRequest.getClauseId());
        if(existingClause.isEmpty()) {
            throw new NoRecordsFoundException("No clause found");
        }

        ApplicableClauses applicableClauses = existingClause.get();

        applicableClauses.setClause(updateApplicableClausesRequest.getClause());
        applicableClausesRepository.save(applicableClauses);

        ApplicableClausesResponse updateApplicableClausesResponse = new ApplicableClausesResponse();
        updateApplicableClausesResponse.setClause(applicableClauses.getClause());

        return ApiResponse.builder()
                .message("Clause updated successfully!")
                .data(updateApplicableClausesRequest)
                .status(String.valueOf(HttpStatus.OK.value()))
                .build();
    }
}
