package com.example.powerstationmanagesystem.controller.request;

import lombok.Data;

@Data
public class PersonRequest {
    private Integer userId;
    private String userName;
    private String telephone;
    private String password;
}
