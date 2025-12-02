package org.khanguhizi.insurancedetails.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class ApiResponse {
    String message;
    private String status;
    private Object data;

}
