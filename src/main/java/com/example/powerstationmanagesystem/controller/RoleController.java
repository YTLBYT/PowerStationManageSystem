package com.example.powerstationmanagesystem.controller;

import com.example.powerstationmanagesystem.common.Result;
import com.example.powerstationmanagesystem.controller.request.RoleRequest;
import com.example.powerstationmanagesystem.controller.request.TroubleRequest;
import com.example.powerstationmanagesystem.service.RoleService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/role")
public class RoleController {
    @Resource
    RoleService roleService;

    @GetMapping("/page")
    public Result pageRole(RoleRequest request){
        return  Result.success(roleService.selectPageRole(request));
    }
}
