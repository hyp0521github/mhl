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

    // 查询某个餐桌的状态信息
    public Object queryDining(int id) {
        return diningDao.queryScalar("select status from dining where id = ?", Dining.class, id);
    }

    // 修改某个餐桌信息
    public int updateDining(int id, String orderName, String orderTel) {
        return diningDao.update("update dining set status = '已预定', orderName = ?, orderTel = ? where id = ?", orderName, orderTel, id);
    }

    // 修改餐桌状态
    public boolean updateDiningStatus(int id) {
        int update = diningDao.update("update dining set status = '用餐中' where id = ?", id);
        return update > 0;
    }
}
