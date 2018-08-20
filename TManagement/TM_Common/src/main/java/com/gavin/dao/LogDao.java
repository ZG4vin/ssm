package com.gavin.dao;

import com.gavin.bean.Log;
import org.apache.ibatis.annotations.Insert;

public interface LogDao {

    @Insert("insert into logs(method,info,create_time) values(#{method},#{info},#{createTime})")
    void insert(Log log);
}
