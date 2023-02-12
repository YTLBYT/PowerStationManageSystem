package com.example.powerstationmanagesystem.dao;

import com.example.powerstationmanagesystem.controller.request.RoleRequest;
import com.example.powerstationmanagesystem.entiy.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDao {
    public List<Role> selectPageRole(RoleRequest roleRequest);
}
