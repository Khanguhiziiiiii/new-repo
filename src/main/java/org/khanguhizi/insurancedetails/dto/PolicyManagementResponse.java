package org.khanguhizi.insurancedetails.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyManagementResponse {
    private Long policyId;
    private String bank;
    private String insurerPolicyNumber;
    private String internalPolicyNumber;
    private String binderName;
    private String riskNoteNo;
    private String currency;
    private LocalDate coverFrom;
    private LocalDate coverTo;
    private LocalDate riskCoverFrom;
    private LocalDate riskCoverTo;
    private boolean active;
}
