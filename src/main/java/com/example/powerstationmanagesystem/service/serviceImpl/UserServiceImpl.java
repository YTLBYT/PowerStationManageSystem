package com.example.powerstationmanagesystem.service.serviceImpl;

import cn.hutool.core.util.ObjectUtil;
import com.example.powerstationmanagesystem.Exception.ServiceException;
import com.example.powerstationmanagesystem.Utils.TokenUtils;
import com.example.powerstationmanagesystem.controller.request.LoginRequest;
import com.example.powerstationmanagesystem.controller.request.PasswordRequest;
import com.example.powerstationmanagesystem.controller.request.PersonRequest;
import com.example.powerstationmanagesystem.controller.request.UserRequest;
import com.example.powerstationmanagesystem.dao.UserDao;
import com.example.powerstationmanagesystem.entiy.LoginDTO;
import com.example.powerstationmanagesystem.entiy.User;
import com.example.powerstationmanagesystem.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 杨涛
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;


    @Override
    public int addUser(User user) {
        if (userDao.getUserByTelephone(user.getTelephone()) != null){
            throw new ServiceException("该手机号账号已经存在");
        }
        return userDao.addUser(user);
    }

    @Override
    public Object selectUserList(UserRequest userRequest) {
        PageHelper.startPage(userRequest.getPageNum(), userRequest.getPageSize());
        List<User> users = userDao.selectUserList(userRequest);
        return new PageInfo<>(users);
    }

    @Override
    public int updateUser(User user) {
        User userByTelephone = userDao.getUserByTelephone(user.getTelephone());
        if ( userByTelephone != null && !userByTelephone.getUserId().equals(user.getUserId())){
            throw new ServiceException("该手机号账号已经存在");
        }
        return userDao.updateUser(user);
    }

    @Override
    public int deleteUser(Integer userId) {
        return userDao.deleteUser(userId);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer resetUserPassword(Integer userId) {
        return userDao.resetUserPassword(userId);
    }

    @Override
    public LoginDTO login(LoginRequest loginRequest) {
        LoginDTO user = userDao.login(loginRequest);
        if (user == null){
            //抛出错误信息
            throw new ServiceException("用户名或密码错误");
        }
        if (user.getStatus() == 0){
            throw new ServiceException("当前用户被禁用");
        }
        user.setToken(TokenUtils.sign(loginRequest));
        return user;
    }

    @Override
    public Integer personUpdate(PersonRequest personRequest) {
        User userByTelephone = userDao.getUserByTelephone(personRequest.getTelephone());
        if ( userByTelephone != null && !userByTelephone.getUserId().equals(personRequest.getUserId())){
            throw new ServiceException("该手机号账号已经存在");
        }
        Integer integer = userDao.personUpdate(personRequest);
        if (integer == 0){
            throw new ServiceException("密码错误");
        }
        else {
            return integer;
        }
    }

    @Override
    public User getUserByTelephone(String telephone) {
        return userDao.getUserByTelephone(telephone);
    }

    @Override
    public Integer editPassword(PasswordRequest passwordRequest) {
        User user = getUserById(passwordRequest.getUserId());
        if (user.getUserPassword().equals(passwordRequest.getPassword())){
            return userDao.editPassword(passwordRequest);
        }
        else {
            throw new ServiceException("旧密码输入错误");
        }
    }
}
