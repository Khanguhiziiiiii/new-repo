package org.khanguhizi.insurancedetails.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String email;
    private String username;
    private String usernameOrEmail;
    private String password;
}
