package com.example.powerstationmanagesystem.dao;

import com.example.powerstationmanagesystem.controller.request.VideoRequest;
import com.example.powerstationmanagesystem.entiy.Video;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoDao {
    public int addVideo(Video video);
    public List<Video> selectVideoList(VideoRequest videoRequest);
    public int updateVideo(Video video);
    public int deleteVideo(Integer videoId);
    public Video getVideoById(Integer videoId);
    public int online(Integer videoId);
    public int offline();
}

