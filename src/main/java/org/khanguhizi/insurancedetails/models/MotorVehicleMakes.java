package org.khanguhizi.insurancedetails.models;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "motor_vehicle_makes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MotorVehicleMakes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long makeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motor_vehicle_brands", referencedColumnName = "brand")
    private MotorVehicleBrands model;

    @Column(nullable = false, unique = true)
    private String make;
}
