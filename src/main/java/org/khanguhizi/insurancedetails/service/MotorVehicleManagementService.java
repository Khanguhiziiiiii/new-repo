package org.khanguhizi.insurancedetails.service;

import lombok.*;
import org.khanguhizi.insurancedetails.models.*;
import org.khanguhizi.insurancedetails.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.*;
import org.khanguhizi.insurancedetails.dto.*;
import org.khanguhizi.insurancedetails.exceptions.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MotorVehicleManagementService {
    private final MotorVehicleManagementRepository motorVehicleManagementRepository;
    private final ClientManagementRepository clientManagementRepository;

    public ApiResponse addVehicle(MotorVehicleRequest motorVehicleRequest) {
        Optional<ClientDetails> existingClientCheck = clientManagementRepository.findByClientId(motorVehicleRequest.getClient());
        if (existingClientCheck.isEmpty()) {
            throw new NoRecordsFoundException("Client not found!");
        }

        Optional<MotorVehicleDetails> existingVehicleCheck = motorVehicleManagementRepository.findByVehicleRegistration(motorVehicleRequest.getVehicleRegistration());
        if (existingVehicleCheck.isPresent()) {
            throw new DuplicateCredentialsException("Vehicle already exists!");
        }

        ClientDetails client = existingClientCheck.get();

        MotorVehicleDetails vehicle = MotorVehicleDetails.builder()
                .clientId(client)
                .vehicleRegistration(motorVehicleRequest.getVehicleRegistration())
                .make(motorVehicleRequest.getMake())
                .model(motorVehicleRequest.getModel())
                .typeOfBody(motorVehicleRequest.getTypeOfBody())
                .yearOfManufacture(motorVehicleRequest.getYearOfManufacture())
                .numberOfPassengers(motorVehicleRequest.getNumberOfPassengers())
                .logBook(motorVehicleRequest.getLogBook())
                .estimatedValue(motorVehicleRequest.getEstimatedValue())
                .build();
        motorVehicleManagementRepository.save(vehicle);

        MotorVehicleResponse motorVehicleResponse = new MotorVehicleResponse();
        motorVehicleResponse.setMotorId(vehicle.getMotorVehicleId());
        motorVehicleResponse.setClient(vehicle.getClientId().getClientId());
        motorVehicleResponse.setVehicleRegistration(vehicle.getVehicleRegistration());
        motorVehicleResponse.setMake(vehicle.getMake());
        motorVehicleResponse.setModel(vehicle.getModel());
        motorVehicleResponse.setTypeOfBody(vehicle.getTypeOfBody());
        motorVehicleResponse.setYearOfManufacture(vehicle.getYearOfManufacture());
        motorVehicleResponse.setNumberOfPassengers(vehicle.getNumberOfPassengers());
        motorVehicleResponse.setLogBook(vehicle.getLogBook());
        motorVehicleResponse.setEstimatedValue(vehicle.getEstimatedValue());

        return ApiResponse.builder()
                .message("Motor Vehicle added successfully")
                .data(motorVehicleResponse)
                .status(String.valueOf(HttpStatus.CREATED.value()))
                .build();
    }

    public ApiResponse fetchVehicleByClientId(MotorVehicleRequest motorVehicleRequest) {
        Optional<ClientDetails> existingClientCheck = clientManagementRepository.findByClientId(motorVehicleRequest.getClient());
        if (existingClientCheck.isEmpty()) {
            throw new NoRecordsFoundException("Client not found!");
        }

        List<MotorVehicleDetails> existingMotorVehicleList = motorVehicleManagementRepository.findByClientId(motorVehicleRequest.getClient());
        if (existingMotorVehicleList.isEmpty()) {
            throw new NoRecordsFoundException("No vehicles found!");
        }

        List<MotorVehicleResponse> motorVehicleDetailsList = existingMotorVehicleList.stream()
                .map(motorVehicleDetails -> {
                    MotorVehicleResponse motorVehicleResponse = new MotorVehicleResponse();
                    motorVehicleResponse.setMotorId(motorVehicleDetails.getMotorVehicleId());
                    motorVehicleResponse.setClient(existingClientCheck.get().getClientId());
                    motorVehicleResponse.setVehicleRegistration(motorVehicleDetails.getVehicleRegistration());
                    motorVehicleResponse.setMake(motorVehicleDetails.getMake());
                    motorVehicleResponse.setModel(motorVehicleDetails.getModel());
                    motorVehicleResponse.setTypeOfBody(motorVehicleDetails.getTypeOfBody());
                    motorVehicleResponse.setYearOfManufacture(motorVehicleDetails.getYearOfManufacture());
                    motorVehicleResponse.setNumberOfPassengers(motorVehicleDetails.getNumberOfPassengers());
                    motorVehicleResponse.setLogBook(motorVehicleDetails.getLogBook());
                    motorVehicleResponse.setEstimatedValue(motorVehicleDetails.getEstimatedValue());
                    return motorVehicleResponse;
                }).toList();
        return ApiResponse.builder()
                .message("Vehicles retrieved successfully")
                .data(motorVehicleDetailsList)
                .status(String.valueOf(HttpStatus.OK.value()))
                .build();
    }

    public ApiResponse updateVehicleDetails(MotorVehicleRequest motorVehicleRequest) {

        Optional<MotorVehicleDetails> existingVehicle =
                motorVehicleManagementRepository.findByVehicleRegistration(motorVehicleRequest.getVehicleRegistration());

        if (existingVehicle.isEmpty()) {
            throw new NoRecordsFoundException("Vehicle not found!");
        }

        MotorVehicleDetails vehicle = existingVehicle.get();

        //vehicle.getVehicleRegistration(motorVehicleRequest.getVehicleRegistration());
        vehicle.setMake(motorVehicleRequest.getMake());
        vehicle.setModel(motorVehicleRequest.getModel());
        vehicle.setTypeOfBody(motorVehicleRequest.getTypeOfBody());
        vehicle.setYearOfManufacture(motorVehicleRequest.getYearOfManufacture());
        vehicle.setNumberOfPassengers(motorVehicleRequest.getNumberOfPassengers());
        vehicle.setLogBook(motorVehicleRequest.getLogBook());
        vehicle.setEstimatedValue(motorVehicleRequest.getEstimatedValue());

        motorVehicleManagementRepository.save(vehicle);

        MotorVehicleResponse response = new MotorVehicleResponse();
        response.setMotorId(vehicle.getMotorVehicleId());
        response.setClient(vehicle.getClientId().getClientId());
        response.setVehicleRegistration(vehicle.getVehicleRegistration());
        response.setMake(vehicle.getMake());
        response.setModel(vehicle.getModel());
        response.setTypeOfBody(vehicle.getTypeOfBody());
        response.setYearOfManufacture(vehicle.getYearOfManufacture());
        response.setNumberOfPassengers(vehicle.getNumberOfPassengers());
        response.setLogBook(vehicle.getLogBook());
        response.setEstimatedValue(vehicle.getEstimatedValue());

        return ApiResponse.builder()
                .message("Vehicle details updated successfully")
                .data(response)
                .status(String.valueOf(HttpStatus.OK.value()))
                .build();
    }
}
