package org.khanguhizi.insurancedetails.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "client_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Column(nullable = false)
    private String policyHolder;

    @Column(nullable = false)
    private String mobile;

    @Column
    private String tel;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String idNo;

    @Column(nullable = false, unique = true)
    private String kraPin;

    @Column(nullable = false)
    private String postalAddress;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String bankAccNo;
}
