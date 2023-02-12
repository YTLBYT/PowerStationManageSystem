package com.example.powerstationmanagesystem.entiy;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @description trouble
 * @author YT
 * @date 2023-02-12
 */
@Data
public class Trouble{

    /**
     * 故障id
     */
    private Integer troubleId;

    /**
     * 故障描述
     */
    private String troubleDescribe;

    /**
     * 换电站编号
     */
    private String stationNumber;

    /**
     * 故障状态
     */
    private Integer status;

    /**
     * 故障上报时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    /**
     * 故障处理时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date resolveTime;

    /**
     * 换电站地址
     */
    private String stationAddress;

    /**
     * 处理人姓名
     */
    private String userName;

    /**
     * 处理人手机号
     */
    private String telephone;

    public Trouble() {}
}