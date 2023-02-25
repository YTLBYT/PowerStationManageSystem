package com.example.powerstationmanagesystem.controller.request;

import lombok.Data;

@Data
public class PasswordRequest {
    private Integer userId;
    private String userName;
    private String telephone;
    private String password;
    private String newPassword1;
}
