package com.gavin.serivce.impl;

import com.gavin.bean.GroupMenu;
import com.gavin.dao.GroupDao;
import com.gavin.serivce.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    public List<GroupMenu> getGroupMenu(String groupId) {
        return groupDao.getGroupMenu(groupId);
    }
}
