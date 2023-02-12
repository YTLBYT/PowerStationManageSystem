package com.example.powerstationmanagesystem.controller.request;

import lombok.Data;

import java.util.Date;

@Data
public class TroubleRequest extends PageRequest{
    private String stationNumber;
    private String stationAddress;
    private String createTime;
}
