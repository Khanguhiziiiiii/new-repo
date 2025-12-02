package org.khanguhizi.insurancedetails.controller;

import org.khanguhizi.insurancedetails.dto.*;
import org.khanguhizi.insurancedetails.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestController
public class ClientManagementController {
    @Autowired
    private ClientManagementService clientManagementService;

    @PostMapping("/create-client")
    public ResponseEntity<ApiResponse> createClient(@RequestBody ClientDetailsRequest createClientsRequest) {
        var createClientResponse = clientManagementService.createClient(createClientsRequest);
        return ResponseEntity.ok(createClientResponse);
    }

    @GetMapping("/fetch-all-clients")
    public ResponseEntity<ApiResponse> fetchAllClients() {
        var fetchAllClientsResponse = clientManagementService.fetchAllClientsDetails();
        return ResponseEntity.ok(fetchAllClientsResponse);
    }

    @PutMapping("/update-client-details")
    public ResponseEntity<ApiResponse> updateClientDetails(@RequestBody ClientDetailsRequest updateClientDetailsRequest) {
        var updateClientDetailsResponse = clientManagementService.updateClientDetails(updateClientDetailsRequest);
        return ResponseEntity.ok(updateClientDetailsResponse);
    }
}
