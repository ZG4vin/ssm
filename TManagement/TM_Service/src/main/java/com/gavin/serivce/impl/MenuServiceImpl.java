package com.gavin.serivce.impl;

import com.gavin.bean.Menus;
import com.gavin.dao.MenuDao;
import com.gavin.serivce.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;


    public Menus getMenu(String menuId) {
        return menuDao.getMenu(menuId);
    }
}
