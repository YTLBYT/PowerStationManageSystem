package com.example.powerstationmanagesystem.service.serviceImpl;

import com.example.powerstationmanagesystem.controller.request.VideoRequest;
import com.example.powerstationmanagesystem.dao.VideoDao;
import com.example.powerstationmanagesystem.entiy.Video;
import com.example.powerstationmanagesystem.service.VideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Resource
    VideoDao videoDao;


    @Override
    public int addVideo(Video video) {
        return videoDao.addVideo(video);
    }

    @Override
    public Object selectVideoList(VideoRequest videoRequest) {
        PageHelper.startPage(videoRequest.getPageNum(), videoRequest.getPageSize());
        List<Video> videos = videoDao.selectVideoList(videoRequest);
        return new PageInfo<>(videos);
    }

    @Override
    public int updateVideo(Video video) {
        return videoDao.updateVideo(video);
    }

    @Override
    public int deleteVideo(Integer videoId) {
        return videoDao.deleteVideo(videoId);
    }

    @Override
    public Video getVideoById(Integer videoId) {
        return videoDao.getVideoById(videoId);
    }

    @Override
    public int online(Integer videoId) {
        //先将所有下线
        videoDao.offline();
        //再将该视频设为上线
        return videoDao.online(videoId);
    }

}
