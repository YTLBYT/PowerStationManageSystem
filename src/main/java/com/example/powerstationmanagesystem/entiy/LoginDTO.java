package com.example.powerstationmanagesystem.entiy;

import lombok.Data;

@Data
public class LoginDTO {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 所属换电站id
     */
    private String stationNumber;

    /**
     * 状态
     */
    private Integer status;
}
