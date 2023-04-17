package com.example.powerstationmanagesystem.entiy;

import lombok.Data;

import java.util.List;

/**
 * @description station
 * @author YT
 * @date 2023-02-10
 */
@Data
public class Station{


    /**
     * 换电站id
     */
    private Integer stationId;

    /**
     * 换电站编号
     */
    private String stationNumber;

    /**
     * 换电站名
     */
    private String stationName;

    /**
     * 换电站地址
     */
    private String stationAddress;

    /**
     * 可用电池数量
     */
    private Integer chargeNumber;

    /**
     * 总电池数量
     */
    private Integer chargeTotal;

    /**
     * 视频流地址
     */
    private String videoUrl;

    /**
     * 换电站状态
     */
    private Integer status;

    /**
     * 换电站经纬度，根据地址获取，也可以认为修改
     */
    private String stationAltitude;

    /**
     * 换电站服务时长
     */
    private String stationServetime;

    /**
     * 服务平均消费
     */
    private String stationFee;

    private List<Integer> carIdList;

    public Station() {}
}
