package org.khanguhizi.insurancedetails.controller;

import org.khanguhizi.insurancedetails.dto.*;
import org.khanguhizi.insurancedetails.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestController
public class ApplicableClausesController {
    @Autowired
    private ApplicableClausesService applicableClausesService;

    @PostMapping("/create-applicable-clause")
    public ResponseEntity<ApiResponse> createApplicableClause(@RequestBody ApplicableClausesRequest createApplicableClauseRequest) {
        var createApplicableClauseResponse = applicableClausesService.createApplicableClause(createApplicableClauseRequest);
        return new ResponseEntity<>(createApplicableClauseResponse, HttpStatus.CREATED);
    }

    @GetMapping("/fetch-applicable-clauses")
    public ResponseEntity<ApiResponse> fetchApplicableClauses() {
        var fetchApplicableClausesResponse = applicableClausesService.fetchAllApplicableClauses();
        return new ResponseEntity<>(fetchApplicableClausesResponse, HttpStatus.OK);
    }

    @PutMapping("/update-applicable-clauses")
    public ResponseEntity<ApiResponse> updateApplicableClauses(@RequestBody ApplicableClausesRequest updateApplicableClausesRequest) {
        var updateApplicableClausesResponse= applicableClausesService.updateApplicableClause(updateApplicableClausesRequest);
        return new ResponseEntity<>(updateApplicableClausesResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete-applicable-clause")
    public ResponseEntity<ApiResponse> deleteApplicableClauses(@PathVariable Long clauseId) {
        var deleteApplicableClauseResponse = applicableClausesService.deleteApplicableClause(clauseId);
        return new ResponseEntity<>(deleteApplicableClauseResponse, HttpStatus.OK);
    }
}
