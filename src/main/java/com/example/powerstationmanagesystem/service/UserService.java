package com.example.powerstationmanagesystem.service;

import com.example.powerstationmanagesystem.controller.request.UserRequest;
import com.example.powerstationmanagesystem.entiy.User;

import java.util.List;


public interface UserService {
    public int addUser(User user);
    public Object selectUserList(UserRequest userRequest);
    public int updateUser(User user);
    public int deleteUser(Integer userId);
    public User getUserById(Integer userId);
    public Integer resetUserPassword(Integer userId);
}