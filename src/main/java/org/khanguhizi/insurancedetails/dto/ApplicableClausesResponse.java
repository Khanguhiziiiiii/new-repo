package org.khanguhizi.insurancedetails.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicableClausesResponse {
    private Long clauseId;
    private String clause;
}
