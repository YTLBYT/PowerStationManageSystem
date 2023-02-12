package com.example.powerstationmanagesystem.controller;

import com.example.powerstationmanagesystem.common.Result;
import com.example.powerstationmanagesystem.controller.request.TroubleRequest;
import com.example.powerstationmanagesystem.entiy.Trouble;
import com.example.powerstationmanagesystem.service.TroubleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/trouble")
public class TroubleController {

    @Resource
    TroubleService troubleService;

    @PostMapping("/add")
    public Result addTrouble(@RequestBody Trouble trouble){
        return Result.success(troubleService.addTrouble(trouble));
    }

    @GetMapping("/page")
    public Result pageTrouble(TroubleRequest troubleRequest){
        return  Result.success(troubleService.selectTroubleList(troubleRequest));
    }

    @DeleteMapping("/delete/{troubleId}")
    public Result deleteTroubleById(@PathVariable Integer troubleId){
        return Result.success(troubleService.deleteTrouble(troubleId));
    }

    @PutMapping("/update")
    public Result updateTrouble(@RequestBody Trouble trouble){
        return Result.success(troubleService.updateTrouble(trouble));
    }
}
