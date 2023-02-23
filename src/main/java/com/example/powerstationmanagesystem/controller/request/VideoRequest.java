package com.example.powerstationmanagesystem.controller.request;

import lombok.Data;

@Data
public class VideoRequest extends PageRequest{
    private String videoUrl;
    private String stationNumber;
}
