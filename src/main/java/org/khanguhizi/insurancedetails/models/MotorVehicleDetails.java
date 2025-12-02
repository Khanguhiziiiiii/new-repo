package org.khanguhizi.insurancedetails.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "motor_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MotorVehicleDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long motorVehicleId;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClientDetails clientId;

    @Column
    private String vehicleRegistration;

    @Column
    private String make;

    @Column
    private String model;

    @Column
    private String typeOfBody;

    @Column
    private String yearOfManufacture;

    @Column
    private String numberOfPassengers;

    @Column
    private String logBook;

    @Column
    private int estimatedValue;
}
