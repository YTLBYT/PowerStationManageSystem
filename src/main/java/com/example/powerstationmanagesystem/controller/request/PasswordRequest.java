package com.example.powerstationmanagesystem.controller.request;

import lombok.Data;

@Data
public class PasswordRequest {
    private Integer userId;
    private String password;
    private String newPassword;
}
