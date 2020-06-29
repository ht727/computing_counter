package com.huangliutan.service.impl;

import com.huangliutan.dao.SaveDao;
import com.huangliutan.entity.Records;
import com.huangliutan.service.SaveService;

public class SaveServiceImpl implements SaveService {
    SaveDao saveDao = null;

    public void setSaveDao(SaveDao saveDao) {
        this.saveDao = saveDao;
    }

    @Override
    public boolean insert(Records records) {
        int insert = saveDao.insert(records);
        return insert>0;
    }
}
