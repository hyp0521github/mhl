package com.yz.mhl.service;

import com.yz.mhl.dao.BillDao;
import com.yz.mhl.domain.Bill;

import java.util.List;

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

    // 查询所有账单信息
    public List<Bill> queryBillList() {
        return billDao.queryMulti("select * from bill", Bill.class, null);
    }
}
