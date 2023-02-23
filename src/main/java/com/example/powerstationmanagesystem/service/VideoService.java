package com.example.powerstationmanagesystem.service;

import com.example.powerstationmanagesystem.controller.request.VideoRequest;
import com.example.powerstationmanagesystem.entiy.Video;



public interface VideoService {
    public int addVideo(Video video);
    public Object selectVideoList(VideoRequest videoRequest);
    public int updateVideo(Video video);
    public int deleteVideo(Integer videoId);
    public Video getVideoById(Integer videoId);
    public int online(Integer videoId);
}

