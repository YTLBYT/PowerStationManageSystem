package com.example.powerstationmanagesystem.entiy;

import lombok.Data;
/**
 * @description menu
 * @author YT
 * @date 2023-02-10
 */
@Data
public class Menu{

    /**
     * 权限id
     */
    private Integer menuId;

    /**
     * 权限名
     */
    private String menuName;

    /**
     * 权限路径
     */
    private String menuPath;

    /**
     * 父权限id
     */
    private Integer pMenuId;

    /**
     * 权限状态
     */
    private Integer status;

    public Menu() {}
}
