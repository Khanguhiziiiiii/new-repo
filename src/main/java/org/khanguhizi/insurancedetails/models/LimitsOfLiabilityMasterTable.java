package org.khanguhizi.insurancedetails.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "limits_of_liability_master_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LimitsOfLiabilityMasterTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String description;
}
