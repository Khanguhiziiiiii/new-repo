package org.khanguhizi.insurancedetails.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "policy_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PolicyDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String bank;

    @Column(nullable = false, unique =  true )
    private String insurerPolicyNumber;

    @Column(nullable = false, unique =  true )
    private String internalPolicyNumber;

    @Column
    private String binderName;

    @Column(nullable = false, unique = true )
    private String riskNoteNo;

    @Column
    private String currency;

    @Column
    private LocalDate coverFrom;

    @Column
    private LocalDate coverTo;

    @Column
    private LocalDate riskCoverFrom;

    @Column
    private LocalDate riskCoverTo;

    @Column
    private boolean active;
}
