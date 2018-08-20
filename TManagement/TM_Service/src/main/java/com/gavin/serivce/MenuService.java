package com.gavin.serivce;

import com.gavin.bean.Menus;


public interface MenuService {

    //根据传进来的角色菜单list，查出对应的菜单
    Menus getMenu(String menuId);
}
