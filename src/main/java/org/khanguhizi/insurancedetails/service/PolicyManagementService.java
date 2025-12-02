package org.khanguhizi.insurancedetails.service;

import lombok.*;
import org.khanguhizi.insurancedetails.dto.*;
import org.khanguhizi.insurancedetails.exceptions.*;
import org.khanguhizi.insurancedetails.models.*;
import org.khanguhizi.insurancedetails.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PolicyManagementService {
    private final PolicyManagementRepository policyManagementRepository;
    //public ApiResponse createPolicy(PolicyDetails policyNumber) {}

    public ApiResponse fetchPolicyByPolicyNumber(String policyNumber){
        Optional<PolicyDetails> existingPolicyNumber = policyManagementRepository.findByInsurerPolicyNumber(policyNumber);
        if(existingPolicyNumber.isEmpty()){
            throw new NoRecordsFoundException("Invalid Policy Number: " + policyNumber);
        }

        PolicyDetails policyDetails = existingPolicyNumber.get();

        PolicyManagementResponse fetchPolicyDetailsResponse = new PolicyManagementResponse();
        fetchPolicyDetailsResponse.setPolicyId(policyDetails.getId());
        fetchPolicyDetailsResponse.setBank(policyDetails.getBank());
        fetchPolicyDetailsResponse.setInsurerPolicyNumber(policyDetails.getInsurerPolicyNumber());
        fetchPolicyDetailsResponse.setInternalPolicyNumber(policyDetails.getInternalPolicyNumber());
        fetchPolicyDetailsResponse.setBinderName(policyDetails.getBinderName());
        fetchPolicyDetailsResponse.setRiskNoteNo(policyDetails.getRiskNoteNo());
        fetchPolicyDetailsResponse.setCurrency(policyDetails.getCurrency());
        fetchPolicyDetailsResponse.setCoverFrom(policyDetails.getCoverFrom());
        fetchPolicyDetailsResponse.setCoverTo(policyDetails.getCoverTo());
        fetchPolicyDetailsResponse.setRiskCoverFrom(policyDetails.getRiskCoverFrom());
        fetchPolicyDetailsResponse.setRiskCoverTo(policyDetails.getRiskCoverTo());
        fetchPolicyDetailsResponse.setActive(policyDetails.isActive());

        return ApiResponse.builder()
                .message("Policy details fetched successfully!")
                .data(fetchPolicyDetailsResponse)
                .status(String.valueOf(HttpStatus.OK.value()))
                .build();
    }
}
