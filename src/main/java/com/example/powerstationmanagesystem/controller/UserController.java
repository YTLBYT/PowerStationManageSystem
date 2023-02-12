package com.example.powerstationmanagesystem.controller;

import com.example.powerstationmanagesystem.common.Result;
import com.example.powerstationmanagesystem.controller.request.UserRequest;
import com.example.powerstationmanagesystem.entiy.User;
import com.example.powerstationmanagesystem.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/add")
    public Result addUser(@RequestBody User user){
        return Result.success(userService.addUser(user));
    }

    @GetMapping("/page")
    public Result pageUser(UserRequest userRequest){
        return  Result.success(userService.selectUserList(userRequest));
    }

    @DeleteMapping("/delete/{userId}")
    public Result deleteUserById(@PathVariable Integer userId){
        return Result.success(userService.deleteUser(userId));
    }

    @PutMapping("/update")
    public Result updateUser(@RequestBody User user){
        return Result.success(userService.updateUser(user));
    }

    @GetMapping("/{userId}")
    public Result getUserById(@PathVariable Integer userId){
        return Result.success(userService.getUserById(userId));
    }

    @PutMapping("/resetUserPassword/{userId}")
    public Result resetUserPassword(@PathVariable Integer userId){
        return Result.success(userService.resetUserPassword(userId));
    }
}
