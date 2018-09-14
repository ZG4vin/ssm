package com.gavin.dao;

import com.gavin.bean.GroupMenu;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Repository
public interface GroupDao {

    @Select("select * from group_menus where group_id=#{groupId}")
    @Results({
            @Result(column = "group_id",property = "groupId"),
            @Result(column = "menu_id",property = "menuId")
    })
    List<GroupMenu> getGroupMenu(String groupId);
}
