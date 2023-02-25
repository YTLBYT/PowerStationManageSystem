package com.example.powerstationmanagesystem.controller;

import com.example.powerstationmanagesystem.common.Result;
import com.example.powerstationmanagesystem.controller.request.CarRequest;
import com.example.powerstationmanagesystem.entiy.Car;
import com.example.powerstationmanagesystem.service.CarService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 杨涛
 */
@RestController
@CrossOrigin
@RequestMapping("/car")
public class CarController {

    @Resource
    CarService carService;

    @PostMapping("/add")
    public Result addCar(@RequestBody Car car){
        return Result.success(carService.addCar(car));
    }

    @GetMapping("/page")
    public Result pageCar(CarRequest carRequest){
        return  Result.success(carService.selectCarList(carRequest));
    }

    @DeleteMapping("/delete/{carId}")
    public Result deleteCarById(@PathVariable Integer carId){
        return Result.success(carService.deleteCar(carId));
    }

    @PutMapping("/update")
    public Result updateCar(@RequestBody Car car){
        return Result.success(carService.updateCar(car));
    }

    @GetMapping("/{carId}")
    public Result getCarById(@PathVariable Integer carId){
        return Result.success(carService.getCarById(carId));
    }

}
