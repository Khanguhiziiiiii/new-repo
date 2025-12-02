package org.khanguhizi.insurancedetails.controller;

import org.khanguhizi.insurancedetails.dto.*;
import org.khanguhizi.insurancedetails.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestController
public class MotorVehicleManagementController {
    @Autowired
    MotorVehicleManagementService motorVehicleManagementService;

    @PostMapping("/add-vehicle")
    public ResponseEntity<ApiResponse> addMotorVehicle(@RequestBody MotorVehicleRequest addMotorVehicleRequest) {
        var addMotorVehicleResponse = motorVehicleManagementService.addVehicle(addMotorVehicleRequest);
        return new ResponseEntity<>(addMotorVehicleResponse, HttpStatus.CREATED);
    }

    @GetMapping("/get-customer{clientId}-vehicles")
    public ResponseEntity<ApiResponse> getClientVehicles(@PathVariable("clientId") Long clientId) {
        MotorVehicleRequest getMotorVehicleRequest = new MotorVehicleRequest();
        getMotorVehicleRequest.setClient(clientId);

        var getMotorVehicleResponse = motorVehicleManagementService.fetchVehicleByClientId(getMotorVehicleRequest);
        return ResponseEntity.ok(getMotorVehicleResponse);
    }

    @PutMapping("/update-motor-vehicle-details")
    public ResponseEntity<ApiResponse> updateMotorVehicleDetails(@RequestBody MotorVehicleRequest updateMotorVehicleRequest) {
        var updateMotorVehicleResponse = motorVehicleManagementService.updateVehicleDetails(updateMotorVehicleRequest);
        return ResponseEntity.ok(updateMotorVehicleResponse);
    }
}
