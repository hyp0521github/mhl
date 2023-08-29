package com.yz.mhl.service;

import com.yz.mhl.dao.DiningDao;
import com.yz.mhl.domain.Dining;

import java.util.List;

/**
 * @author 院长
 * @version 1.0.0
 */
public class DiningService {
    private DiningDao diningDao = new DiningDao();

    // 查询所有餐桌信息
    public List<Dining> queryDiningList() {
        return diningDao.queryMulti("select id, status from dining", Dining.class, null);
    }
}
