package org.khanguhizi.insurancedetails.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotorVehicleResponse {
    private Long motorId;
    private Long client;
    private String vehicleRegistration;
    private String make;
    private String model;
    private String typeOfBody;
    private String yearOfManufacture;
    private String numberOfPassengers;
    private String logBook;
    private int estimatedValue;
}
