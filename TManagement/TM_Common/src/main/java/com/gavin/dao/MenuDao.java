package com.gavin.dao;

import com.gavin.bean.Menus;
import org.apache.ibatis.annotations.ResultMap;

import javax.annotation.Resource;
@Repository
public interface MenuDao {

    @ResultMap("resultMap")
    Menus getMenu(String menuId);
}
