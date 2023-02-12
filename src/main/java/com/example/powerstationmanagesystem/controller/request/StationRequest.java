package com.example.powerstationmanagesystem.controller.request;

import lombok.Data;

@Data
public class StationRequest extends PageRequest{
    private String stationName;
    private String stationNumber;
}
