package org.khanguhizi.insurancedetails.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicableClausesRequest {
    private Long clauseId;
    private String clause;
}
