package com.example.powerstationmanagesystem.service.serviceImpl;

import com.example.powerstationmanagesystem.controller.request.UserRequest;
import com.example.powerstationmanagesystem.dao.UserDao;
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
}
