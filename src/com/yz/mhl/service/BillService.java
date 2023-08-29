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
    private DiningService ds = new DiningService();

    // 添加账单
    public Boolean addBill(String billId, int menuId, int nums, Double money, int diningId) {
        int update = billDao.update("insert into bill values(null, ?, ?, ?, ?, ?, now(), '未结账')", billId, menuId, nums, money, diningId);
        return update > 0;
    }

    // 查询所有账单信息
    public List<Bill> queryBillList() {
        return billDao.queryMulti("select * from bill", Bill.class, null);
    }

    // 查询是否有未结账信息
    public boolean queryNotPayBill(int diningId) {
        Bill bill = billDao.querySingle("select * from bill where diningId = ? and state = '未结账'", Bill.class, diningId);
        if(bill == null) return false;
        return true;
    }

    // 完成结账
    public boolean payBill(int diningId) {
        // 修改账单信息
        int update = billDao.update("update bill set state = '已结账' where diningId = ? and state = '未结账'", diningId);
        if(update <= 0) return false;
        // 修改餐桌信息
        if(!ds.updateDiningStatusIsEmpty(diningId)) return false;
        return true;
    }
}
