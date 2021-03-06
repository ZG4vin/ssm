package com.gavin.serivce.impl;

import com.gavin.bean.User;
import com.gavin.dao.UserDao;
import com.gavin.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public User getUser(User user) {
        return userDao.getUser(user);
    }

    public List<User> getAllUser(){
        return userDao.getAllUser();
    }
}
