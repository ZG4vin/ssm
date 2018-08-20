package com.gavin.serivce;

import com.gavin.bean.GroupMenu;

import java.util.List;

public interface GroupService {
    //获取角色的拥有的菜单
    List<GroupMenu> getGroupMenu(String groupId);
}
