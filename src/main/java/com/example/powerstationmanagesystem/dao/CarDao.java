package com.example.powerstationmanagesystem.dao;

import com.example.powerstationmanagesystem.controller.request.CarRequest;
import com.example.powerstationmanagesystem.entiy.Car;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarDao {
    public int addCar(Car car);
    public List<Car> selectCarList(CarRequest carRequest);
    public int updateCar(Car car);
    public int deleteCar(Integer carId);
    public Car getCarById(Integer carId);
}

