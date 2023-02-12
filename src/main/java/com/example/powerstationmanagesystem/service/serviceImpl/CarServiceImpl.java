package com.example.powerstationmanagesystem.service.serviceImpl;

import com.example.powerstationmanagesystem.controller.request.CarRequest;
import com.example.powerstationmanagesystem.dao.CarDao;
import com.example.powerstationmanagesystem.entiy.Car;
import com.example.powerstationmanagesystem.service.CarService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Resource
    CarDao carDao;


    @Override
    public int addCar(Car car) {
        return carDao.addCar(car);
    }

    @Override
    public Object selectCarList(CarRequest carRequest) {
        PageHelper.startPage(carRequest.getPageNum(), carRequest.getPageSize());
        List<Car> cars = carDao.selectCarList(carRequest);
        return new PageInfo<>(cars);
    }

    @Override
    public int updateCar(Car car) {
        return carDao.updateCar(car);
    }

    @Override
    public int deleteCar(Integer carId) {
        return carDao.deleteCar(carId);
    }

    @Override
    public Car getCarById(Integer carId) {
        return carDao.getCarById(carId);
    }

}
