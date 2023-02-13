package com.example.powerstationmanagesystem.dao;

import com.example.powerstationmanagesystem.common.Result;
import com.example.powerstationmanagesystem.controller.request.StationRequest;
import com.example.powerstationmanagesystem.entiy.Car;
import com.example.powerstationmanagesystem.entiy.Station;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StationDao {
    public int addStation(Station station);
    public int addStationToCars(Integer stationId, Integer carId);
    public List<Car> getCars();
    public List<String> getCarBrand();
    public Station selectStationByNumber(String stationNumber);
    public List<Station> selectPageStation(StationRequest stationRequest);
    public Integer updateStation(Station station);
    public Integer deleteStation(Integer stationId);
    public Integer deleteStationToCars(Integer stationId);
    public List<Integer> getDefaultCars(Integer stationId);
}
