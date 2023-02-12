package com.example.powerstationmanagesystem.controller.request;

import lombok.Data;

@Data
//提取所有分页查询参数的共性
public class PageRequest {
    //设置默认值，防止没传报错
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}