package com.example.powerstationmanagesystem.service.serviceImpl;

import com.example.powerstationmanagesystem.controller.request.RoleRequest;
import com.example.powerstationmanagesystem.dao.RoleDao;
import com.example.powerstationmanagesystem.entiy.Role;
import com.example.powerstationmanagesystem.entiy.Trouble;
import com.example.powerstationmanagesystem.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleDao roleDao;

    @Override
    public Object selectPageRole(RoleRequest roleRequest) {
        PageHelper.startPage(roleRequest.getPageNum(), roleRequest.getPageSize());
        List<Role> roles = roleDao.selectPageRole(roleRequest);
        return new PageInfo<>(roles);
    }
}
