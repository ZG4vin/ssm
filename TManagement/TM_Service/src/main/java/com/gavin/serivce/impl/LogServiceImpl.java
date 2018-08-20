package com.gavin.serivce.impl;

import com.gavin.bean.Log;
import com.gavin.dao.LogDao;
import com.gavin.serivce.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    public void edit(Log log) {
        logDao.insert(log);
    }
}
