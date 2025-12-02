package org.khanguhizi.insurancedetails.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDetailsRequest {
    private String policyHolder;
    private String mobile;
    private String tel;
    private String email;
    private String idNo;
    private String kraPin;
    private String postalAddress;
    private String postalCode;
    private String bankAccNo;
}
