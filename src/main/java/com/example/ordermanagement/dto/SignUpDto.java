package com.example.ordermanagement.dto;

import lombok.Data;

@Data
public class SignUpDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String bornAt;
}
