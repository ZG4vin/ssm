package com.gavin.serivce.impl;

import com.gavin.bean.Img;
import com.gavin.dao.ImgDao;
import com.gavin.serivce.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImgServiceImpl implements ImgService {
    @Autowired
    private ImgDao imgDao;
    public void insert(Img img) {
        imgDao.insert(img);
    }

    public void delete() {
        imgDao.delete();
    }
}
