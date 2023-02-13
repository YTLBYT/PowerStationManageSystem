package com.example.powerstationmanagesystem.controller;

import com.example.powerstationmanagesystem.common.Result;
import com.example.powerstationmanagesystem.controller.request.StationRequest;
import com.example.powerstationmanagesystem.entiy.Station;
import com.example.powerstationmanagesystem.service.StationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/station")
public class StationController {
    @Resource
    StationService stationService;

    @GetMapping("/getCarList")
    public Result getCarList(){
        return Result.success(stationService.getCars());
    }



    @PostMapping("/add")
    public Result addStation(@RequestBody Station station){
        return Result.success(stationService.addStation(station));
    }

    @GetMapping("/page")
    public Result getPageStation(StationRequest stationRequest){
        Object pageStation = stationService.selectPageStation(stationRequest);
        if (pageStation != null){
            return Result.success(pageStation);
        }
        else {
            return Result.error("查询失败");
        }
    }

    @GetMapping("/{stationNumber}")
    public Result getStationByNumber(@PathVariable String stationNumber){
        return Result.success(stationService.selectStationByNumber(stationNumber));
    }

    @PutMapping("/update")
    public Result update(@RequestBody Station station){
        return Result.success(stationService.updateStation(station));
    }

    @DeleteMapping("/delete/{stationId}")
    public Result delete(@PathVariable Integer stationId){
        return Result.success(stationService.deleteStation(stationId));
    }

    @GetMapping("carTreeList")
    public Result carTreeList(){
        return Result.success(stationService.getTreeCars());
    }

    @GetMapping("/car/{stationId}")
    public Result getDefaultCars(@PathVariable Integer stationId){
        return Result.success(stationService.getDefaultCars(stationId));
    }
}
