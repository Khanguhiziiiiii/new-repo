package org.khanguhizi.insurancedetails.models;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "motor_vehicle_brands")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MotorVehicleBrands {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandId;

    @Column(nullable = false, unique = true)
    private String brand;
}
