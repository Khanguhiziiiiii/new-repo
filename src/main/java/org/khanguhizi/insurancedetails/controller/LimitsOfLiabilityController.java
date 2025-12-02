package org.khanguhizi.insurancedetails.controller;

import org.khanguhizi.insurancedetails.dto.*;
import org.khanguhizi.insurancedetails.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestController
public class LimitsOfLiabilityController {
    @Autowired
    private LimitsOfLiabilityService limitsOfLiabilityService;

    @PostMapping("/add-limits-of-liability-description")
    public ResponseEntity<ApiResponse> createLimitsOfLiabilityDescriptionDetailsOnMasterTable(@RequestBody LimitsOfLiabilityMasterTableRequest createLimitsOfLiabilityDescriptionRequest){
        var createLimitsOfLiabilityDescriptionResponse = limitsOfLiabilityService.createLimitsOfLiabilityDescriptionDetailsOnMasterTable(createLimitsOfLiabilityDescriptionRequest);
        return new ResponseEntity<>(createLimitsOfLiabilityDescriptionResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update-limits-of-liability-description")
    public ResponseEntity<ApiResponse> updateLimitsOfLiabilityDescriptionDetailsOnMasterTable(@RequestBody LimitsOfLiabilityMasterTableRequest updateLimitsOfLiabilityDescriptionRequest){
        var updateLimitsOfLiabilityDescriptionResponse = limitsOfLiabilityService.updateLimitsOfLiabilityDescriptionDetailsOnMasterTable(updateLimitsOfLiabilityDescriptionRequest);
        return new ResponseEntity<>(updateLimitsOfLiabilityDescriptionResponse, HttpStatus.OK);
    }

    @GetMapping("/fetch-limits-of-liability-description")
    public ResponseEntity<ApiResponse> fetchLimitsOfLiabilityDescriptionDetailsOnMasterTable(){
        var fetchLimitsOfLiabilityDescriptionResponse = limitsOfLiabilityService.fetchLimitsOfLiabilityDescriptionDetailsOnMasterTable();
        return new ResponseEntity<>(fetchLimitsOfLiabilityDescriptionResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete-limits-of-liability-description")
    public ResponseEntity<ApiResponse> deleteLimitsOfLiabilityDescriptionDetailsOnMasterTable(@PathVariable Long id){
        var deleteLimitsOfLiabilityDescriptionResponse = limitsOfLiabilityService.deleteLimitsOfLiabilityDescriptionDetailsOnMasterTable(id);
        return new ResponseEntity<>(deleteLimitsOfLiabilityDescriptionResponse, HttpStatus.OK);
    }

    @PostMapping("/add-limit-of-liability")
    public ResponseEntity<ApiResponse> createLimitsOfLiability(@RequestBody LimitsOfLiabilityRequest createLimitsOfLiabilityRequest){
        var createLimitsOfLiabilityResponse = limitsOfLiabilityService.createLimitsOfLiability(createLimitsOfLiabilityRequest);
        return new ResponseEntity<>(createLimitsOfLiabilityResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update-limit-of-liability")
    public ResponseEntity<ApiResponse> updateLimitOfLiability(@RequestBody LimitsOfLiabilityRequest updateLimitsOfLiabilityRequest){
        var updateLimitsOfLiabilityResponse = limitsOfLiabilityService.updateLimitsOfLiability(updateLimitsOfLiabilityRequest);
        return new ResponseEntity<>(updateLimitsOfLiabilityResponse, HttpStatus.OK);
    }

    @GetMapping("/fetch-limits-of-liability/{policyNumber}")
    public ResponseEntity<ApiResponse> fetchLimitsOfLiability(@PathVariable String policyNumber){
        var fetchLimitsOfLiabilityResponse = limitsOfLiabilityService.fetchLimitsOfLiabilityByPolicyNumber(policyNumber);
        return new ResponseEntity<>(fetchLimitsOfLiabilityResponse, HttpStatus.OK);
    }
}
