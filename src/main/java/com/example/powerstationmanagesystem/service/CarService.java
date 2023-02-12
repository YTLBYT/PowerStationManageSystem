package com.example.powerstationmanagesystem.service;

import com.example.powerstationmanagesystem.controller.request.CarRequest;
import com.example.powerstationmanagesystem.entiy.Car;



public interface CarService {
    public int addCar(Car car);
    public Object selectCarList(CarRequest carRequest);
    public int updateCar(Car car);
    public int deleteCar(Integer carId);
    public Car getCarById(Integer carId);
}

