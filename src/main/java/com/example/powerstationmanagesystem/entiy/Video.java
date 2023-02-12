package com.example.powerstationmanagesystem.entiy;

import lombok.Data;

/**
 * @description  video
 * @author YT
 * @date 2023-02-10
 */
@Data
public class Video{

    /**
     * 视频id
     */
    private Integer videoId;

    /**
     * 视频流地址
     */
    private String videoUrl;

    /**
     * 来源
     */
    private String origin;

    /**
     * 流传输协议
     */
    private String agreement;

    /**
     * 入口带宽
     */
    private String bandWidth;

    /**
     * 实时排队车辆结果
     */
    private Integer carNumber;

    /**
     * 状态
     */
    private Integer status;

    public  Video() {}
}
