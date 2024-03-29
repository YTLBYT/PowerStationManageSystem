package com.example.powerstationmanagesystem.dao;

import com.example.powerstationmanagesystem.controller.request.LoginRequest;
import com.example.powerstationmanagesystem.controller.request.PasswordRequest;
import com.example.powerstationmanagesystem.controller.request.PersonRequest;
import com.example.powerstationmanagesystem.controller.request.UserRequest;
import com.example.powerstationmanagesystem.entiy.LoginDTO;
import com.example.powerstationmanagesystem.entiy.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    public int addUser(User user);
    public List<User> selectUserList(UserRequest userRequest);
    public int updateUser(User user);
    public int deleteUser(Integer userId);
    public User getUserById(Integer userId);
    public User getUserByTelephone(String telephone);
    public Integer resetUserPassword(Integer Userid);
    public LoginDTO login(LoginRequest loginRequest);
    public Integer personUpdate(PersonRequest personRequest);
    public Integer editPassword(PasswordRequest passwordRequest);
}
