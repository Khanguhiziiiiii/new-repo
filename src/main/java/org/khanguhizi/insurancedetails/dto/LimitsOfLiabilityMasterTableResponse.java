package org.khanguhizi.insurancedetails.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimitsOfLiabilityMasterTableResponse {
    private Long id; //id on master table
    private String description; //description on both tables
    private String limitsOfLiability; //limitsOfLiability on junior table
    private String excess; //excess on junior table
}
