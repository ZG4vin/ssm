package com.gavin.dao;

import com.gavin.bean.User;
import org.apache.ibatis.annotations.Select;

import javax.annotation.Resource;
import java.util.List;

@Repository
public interface UserDao {

    @Select("select * from user where username=#{username} and password=#{password}")
    User getUser(User user);

    @Select("select * from user")
    List<User> getAllUser();
}
