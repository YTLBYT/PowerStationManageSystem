package com.example.powerstationmanagesystem.service;

import com.example.powerstationmanagesystem.controller.request.StationRequest;
import com.example.powerstationmanagesystem.entiy.Car;
import com.example.powerstationmanagesystem.entiy.LoginDTO;
import com.example.powerstationmanagesystem.entiy.Station;

import java.util.List;

public interface StationService {
    public int addStation(Station station);
    public List<Car> getCars();
    public Object selectPageStation(StationRequest stationRequest);
    public Station selectStationByNumber(String stationNumber);
    public Integer updateStation(Station station);
    public Integer deleteStation(Integer stationId);
    public List<Object> getTreeCars();
    public List<Integer> getDefaultCars(Integer stationId);
}
