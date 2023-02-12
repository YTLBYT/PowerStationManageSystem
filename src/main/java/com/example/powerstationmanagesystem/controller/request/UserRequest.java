package com.example.powerstationmanagesystem.controller.request;

import lombok.Data;

@Data
public class UserRequest extends PageRequest{
    private String userName;
    private String telephone;
}
