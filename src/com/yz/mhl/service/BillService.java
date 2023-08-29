package com.yz.mhl.service;

import com.yz.mhl.dao.BillDao;

/**
 * @author 院长
 * @version 1.0.0
 */
public class BillService {
    private BillDao billDao = new BillDao();

    // 添加账单
    public Boolean addBill(String billId, int menuId, int nums, Double money, int diningId) {
        int update = billDao.update("insert into bill values(null, ?, ?, ?, ?, ?, now(), '未结账')", billId, menuId, nums, money, diningId);
        return update > 0;
    }
}
