package com.example.powerstationmanagesystem.service.serviceImpl;

import com.example.powerstationmanagesystem.Exception.ServiceException;
import com.example.powerstationmanagesystem.controller.request.LoginRequest;
import com.example.powerstationmanagesystem.controller.request.PasswordRequest;
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

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;


    @Override
    public int addUser(User user) {
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
        return user;
    }

    @Override
    public Integer personUpdate(PasswordRequest passwordRequest) {
        Integer integer = userDao.personUpdate(passwordRequest);
        if (integer == 0){
            throw new ServiceException("密码错误");
        }
        else {
            return integer;
        }
    }
}
