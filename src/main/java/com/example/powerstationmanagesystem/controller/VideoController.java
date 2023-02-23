package com.example.powerstationmanagesystem.controller;

import com.example.powerstationmanagesystem.common.Result;
import com.example.powerstationmanagesystem.controller.request.VideoRequest;
import com.example.powerstationmanagesystem.entiy.Video;
import com.example.powerstationmanagesystem.service.VideoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/video")
public class VideoController {

    @Resource
    VideoService videoService;

    @PostMapping("/add")
    public Result addVideo(@RequestBody Video video){
        return Result.success(videoService.addVideo(video));
    }

    @GetMapping("/page")
    public Result pageVideo(VideoRequest videoRequest){
        return  Result.success(videoService.selectVideoList(videoRequest));
    }

    @DeleteMapping("/delete/{videoId}")
    public Result deleteVideoById(@PathVariable Integer videoId){
        return Result.success(videoService.deleteVideo(videoId));
    }

    @PutMapping("/update")
    public Result updateVideo(@RequestBody Video video){
        return Result.success(videoService.updateVideo(video));
    }

    @GetMapping("/{videoId}")
    public Result getVideoById(@PathVariable Integer videoId){
        return Result.success(videoService.getVideoById(videoId));
    }

    @PutMapping("/online/{videoId}")
    public Result online(@PathVariable Integer videoId){
        return Result.success(videoService.online(videoId));
    }

}
