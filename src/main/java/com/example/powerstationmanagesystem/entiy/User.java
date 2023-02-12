package com.example.powerstationmanagesystem.entiy;

import lombok.Data;
/**
 * @description user
 * @author YT
 * @date 2023-02-10
 */
@Data
public class User{


    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPassword;

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


    public User() {}
}