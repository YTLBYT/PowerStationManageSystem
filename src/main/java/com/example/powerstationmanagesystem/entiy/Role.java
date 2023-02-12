package com.example.powerstationmanagesystem.entiy;

import lombok.Data;

@Data
public class Role{

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 角色名
     */
    private String roleName;

    public Role() {}
}
