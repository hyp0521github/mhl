package com.yz.mhl.service;

import com.yz.mhl.dao.MenuDao;
import com.yz.mhl.domain.Menu;

import java.util.List;

/**
 * @author 院长
 * @version 1.0.0
 */
public class MenuService {
    private MenuDao menuDao = new MenuDao();

    // 查询所有菜单
    public List<Menu> queryMenuList() {
        String sql = "select * from menu";
        return menuDao.queryMulti(sql, Menu.class, null);
    }
}
