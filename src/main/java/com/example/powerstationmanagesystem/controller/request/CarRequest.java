package com.example.powerstationmanagesystem.controller.request;

import lombok.Data;

@Data
public class CarRequest extends PageRequest{
    private String carBrand;
    private String carType;
}
