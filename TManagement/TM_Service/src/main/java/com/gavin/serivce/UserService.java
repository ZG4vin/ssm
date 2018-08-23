package com.gavin.serivce;

import com.gavin.bean.User;

import java.util.List;

public interface UserService {
    User getUser(User user);

    List<User> getAllUser();
}
