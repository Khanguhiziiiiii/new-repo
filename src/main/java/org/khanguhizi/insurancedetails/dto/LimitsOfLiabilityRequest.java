package org.khanguhizi.insurancedetails.dto;

import org.khanguhizi.insurancedetails.models.LimitsOfLiabilityMasterTable;
import lombok.*;
import org.khanguhizi.insurancedetails.models.PolicyDetails;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimitsOfLiabilityRequest {
    private Long id; //id on master table
    private LimitsOfLiabilityMasterTable description; //description on both tables
    private String limitsOfLiability; //limitsOfLiability on junior table
    private String excess; //excess on junior table
    private PolicyDetails policyNumber;
}
