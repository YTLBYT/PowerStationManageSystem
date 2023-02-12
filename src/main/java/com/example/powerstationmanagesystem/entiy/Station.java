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
     * 视频流id
     */
    private Integer videoId;

    /**
     * 换电站状态
     */
    private Integer status;

    private List<Integer> carIdList;

    public Station() {}
}
