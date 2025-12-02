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
public class LimitsOfLiabilityService {
    private LimitsOfLiabilityMasterTableRepository limitsOfLiabilityMasterTableRepository;
    private LimitsOfLiabilityRepository limitsOfLiabilityRepository;
    private PolicyManagementRepository policyManagementRepository;

    //THESE APIS FILL DATA INTO THE LIMITS OF LIABILITY MASTER TABLE
    public ApiResponse createLimitsOfLiabilityDescriptionDetailsOnMasterTable(LimitsOfLiabilityMasterTableRequest createLimitsOfLiabilityDescriptionRequest){
        Optional<LimitsOfLiabilityMasterTable> existingDescription = limitsOfLiabilityMasterTableRepository.findByDescription(createLimitsOfLiabilityDescriptionRequest.getDescription());
        if(existingDescription.isPresent()){
            throw new DuplicateCredentialsException("Description already exists");
        }

        LimitsOfLiabilityMasterTable limitsOfLiabilityMasterTable = LimitsOfLiabilityMasterTable.builder()
                .description(createLimitsOfLiabilityDescriptionRequest.getDescription())
                .build();
        limitsOfLiabilityMasterTableRepository.save(limitsOfLiabilityMasterTable);

        LimitsOfLiabilityMasterTableResponse createLimitsOfLiabilityDescriptionResponse = new LimitsOfLiabilityMasterTableResponse();
        createLimitsOfLiabilityDescriptionResponse.setDescription(createLimitsOfLiabilityDescriptionRequest.getDescription());

        return ApiResponse.builder()
                .message("Description created successfully!")
                .data(createLimitsOfLiabilityDescriptionResponse)
                .status(String.valueOf(HttpStatus.OK.value()))
                .build();
    }

    public ApiResponse fetchLimitsOfLiabilityDescriptionDetailsOnMasterTable(){
        List<LimitsOfLiabilityMasterTable> limitsOfLiabilityDescriptionList = limitsOfLiabilityMasterTableRepository.findAll();

        if(limitsOfLiabilityDescriptionList.isEmpty()) {
            throw new NoRecordsFoundException("No records found");
        }

        List<LimitsOfLiabilityMasterTableResponse> allLimitsOfLiabilityDescriptions = limitsOfLiabilityDescriptionList.stream()
                .map(limitsOfLiabilityMasterTable -> {
                    LimitsOfLiabilityMasterTableResponse fetchLimitsOfLiabilityDescriptionResponse = new LimitsOfLiabilityMasterTableResponse();
                    fetchLimitsOfLiabilityDescriptionResponse.setDescription(limitsOfLiabilityMasterTable.getDescription());
                    return fetchLimitsOfLiabilityDescriptionResponse;
                })
                .toList();

        return ApiResponse.builder()
                .message("Limits Of Liability Descriptions Fetched Successfully!")
                .data(allLimitsOfLiabilityDescriptions)
                .status(String.valueOf(HttpStatus.OK.value()))
                .build();
    }

    public ApiResponse deleteLimitsOfLiabilityDescriptionDetailsOnMasterTable(Long id){
        Optional<LimitsOfLiabilityMasterTable> existingLimitOfLiabilityDescription = limitsOfLiabilityMasterTableRepository.findById(id);
        if(existingLimitOfLiabilityDescription.isEmpty()) {
            throw new NoRecordsFoundException("No records found");
        }
        limitsOfLiabilityMasterTableRepository.deleteById(id);

        return ApiResponse.builder()
                .message("Description deleted successfully")
                .status(String.valueOf(HttpStatus.OK.value()))
                .build();
    }

    public ApiResponse updateLimitsOfLiabilityDescriptionDetailsOnMasterTable(LimitsOfLiabilityMasterTableRequest updateLimitsOfLiabilityDescriptionRequest){
        Optional<LimitsOfLiabilityMasterTable> existingLimitOfLiabilityDescription = limitsOfLiabilityMasterTableRepository.findById(updateLimitsOfLiabilityDescriptionRequest.getId());
        if(existingLimitOfLiabilityDescription.isEmpty()) {
            throw new NoRecordsFoundException("No records found");
        }

        LimitsOfLiabilityMasterTable limitsOfLiabilityMasterTable = existingLimitOfLiabilityDescription.get();

        limitsOfLiabilityMasterTable.setDescription(updateLimitsOfLiabilityDescriptionRequest.getDescription());
        limitsOfLiabilityMasterTableRepository.save(limitsOfLiabilityMasterTable);

        LimitsOfLiabilityMasterTableResponse updateLimitsOfLiabilityDescriptionResponse = new LimitsOfLiabilityMasterTableResponse();
        updateLimitsOfLiabilityDescriptionResponse.setDescription(updateLimitsOfLiabilityDescriptionRequest.getDescription());

        return ApiResponse.builder()
                .message("Description updated successfully")
                .data(updateLimitsOfLiabilityDescriptionResponse)
                .status(String.valueOf(HttpStatus.OK.value()))
                .build();
    }



    // THE FOLLOWING APIS FILL DATA INTO THE LIMITS OF LIABILITY TABLE
    public ApiResponse createLimitsOfLiability(LimitsOfLiabilityRequest createLimitsOfLiabilityRequest){
        //check if the description exists on the master table
        Optional<LimitsOfLiabilityMasterTable> existingDescriptionInMasterTable = limitsOfLiabilityMasterTableRepository.findByDescription(String.valueOf(createLimitsOfLiabilityRequest.getDescription()));
        if(existingDescriptionInMasterTable.isEmpty()) {
            throw new NoRecordsFoundException("Invalid description!");
        }

       //check if the description exists in current table
        Optional<LimitsOfLiability> existingDescriptionByPolicyNumber = limitsOfLiabilityRepository.findByPolicyNumberAndDescription((createLimitsOfLiabilityRequest.getPolicyNumber()), (createLimitsOfLiabilityRequest.getDescription()));
        if(existingDescriptionByPolicyNumber.isPresent()) {
            throw new NoRecordsFoundException("Description already added to this policy number!");
        }

        LimitsOfLiability limitsOfLiability = LimitsOfLiability.builder()
                .description(createLimitsOfLiabilityRequest.getDescription())
                .limitsOfLiability(createLimitsOfLiabilityRequest.getLimitsOfLiability())
                .excess(createLimitsOfLiabilityRequest.getExcess())
                .policyNumber(createLimitsOfLiabilityRequest.getPolicyNumber())
                .build();
        limitsOfLiabilityRepository.save(limitsOfLiability);

        LimitsOfLiabilityResponse createLimitsOfLiabilityResponse = new LimitsOfLiabilityResponse();
        createLimitsOfLiabilityResponse.setId(limitsOfLiability.getId());
        createLimitsOfLiabilityResponse.setDescription(limitsOfLiability.getDescription());
        createLimitsOfLiabilityResponse.setLimitsOfLiability(limitsOfLiability.getLimitsOfLiability());
        createLimitsOfLiabilityResponse.setExcess(limitsOfLiability.getExcess());
        createLimitsOfLiabilityResponse.setPolicyNumber(limitsOfLiability.getPolicyNumber());

        return ApiResponse.builder()
                .message("Added successfully")
                .data(createLimitsOfLiabilityResponse)
                .status(String.valueOf(HttpStatus.OK.value()))
                .build();
    }

    public ApiResponse updateLimitsOfLiability(LimitsOfLiabilityRequest updateLimitsOfLiabilityRequest){
        Optional<LimitsOfLiability> existingDescriptionByPolicyNumber = limitsOfLiabilityRepository.findByPolicyNumberAndDescription((updateLimitsOfLiabilityRequest.getPolicyNumber()), (updateLimitsOfLiabilityRequest.getDescription()));
        if(existingDescriptionByPolicyNumber.isPresent()) {
            throw new NoRecordsFoundException("Description already added to this policy number!");
        }

        LimitsOfLiability limitsOfLiability = existingDescriptionByPolicyNumber.get();

        limitsOfLiability.setDescription(updateLimitsOfLiabilityRequest.getDescription());
        limitsOfLiability.setLimitsOfLiability(updateLimitsOfLiabilityRequest.getLimitsOfLiability());
        limitsOfLiability.setExcess(updateLimitsOfLiabilityRequest.getExcess());
        limitsOfLiability.setPolicyNumber(updateLimitsOfLiabilityRequest.getPolicyNumber());
        limitsOfLiabilityRepository.save(limitsOfLiability);

        LimitsOfLiabilityResponse updateLimitsOfLiabilityResponse = new LimitsOfLiabilityResponse();
        updateLimitsOfLiabilityResponse.setDescription(limitsOfLiability.getDescription());
        updateLimitsOfLiabilityResponse.setLimitsOfLiability(limitsOfLiability.getLimitsOfLiability());
        updateLimitsOfLiabilityResponse.setExcess(limitsOfLiability.getExcess());
        updateLimitsOfLiabilityResponse.setPolicyNumber(limitsOfLiability.getPolicyNumber());

        return ApiResponse.builder()
                .message("Updated successfully")
                .data(updateLimitsOfLiabilityResponse)
                .status(String.valueOf(HttpStatus.OK.value()))
                .build();
    }

    public ApiResponse fetchLimitsOfLiabilityByPolicyNumber(String policyNumber){
        Optional<PolicyDetails> existingPolicyNumber = policyManagementRepository.findByInsurerPolicyNumber(policyNumber);
        if(existingPolicyNumber.isEmpty()) {
            throw new NoRecordsFoundException("Invalid policy number!");
        }

        List<LimitsOfLiability> existingLimitsOfLiability = limitsOfLiabilityRepository.findByPolicyNumber(policyNumber);
        if(existingLimitsOfLiability.isEmpty()) {
            throw new NoRecordsFoundException("No policy found!");
        }

        List<LimitsOfLiabilityResponse> limitsOfLiabilityList= existingLimitsOfLiability.stream()
                .map(limitsOfLiability ->{
                    LimitsOfLiabilityResponse fetchLimitsOfLiabilityResponse = new LimitsOfLiabilityResponse();
                    fetchLimitsOfLiabilityResponse.setId(limitsOfLiability.getId());
                    fetchLimitsOfLiabilityResponse.setDescription(limitsOfLiability.getDescription());
                    fetchLimitsOfLiabilityResponse.setLimitsOfLiability(limitsOfLiability.getLimitsOfLiability());
                    fetchLimitsOfLiabilityResponse.setExcess(limitsOfLiability.getExcess());
                    fetchLimitsOfLiabilityResponse.setPolicyNumber(limitsOfLiability.getPolicyNumber());
                    return fetchLimitsOfLiabilityResponse;
                })
                .toList();

        return ApiResponse.builder()
                .message("Successfully fetched")
                .data(limitsOfLiabilityList)
                .status(String.valueOf(HttpStatus.OK.value()))
                .build();
    }
}
