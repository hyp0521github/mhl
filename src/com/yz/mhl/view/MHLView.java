package com.yz.mhl.view;


import com.yz.mhl.domain.Dining;
import com.yz.mhl.domain.Employee;
import com.yz.mhl.domain.Menu;
import com.yz.mhl.service.BillService;
import com.yz.mhl.service.DiningService;
import com.yz.mhl.service.EmployeeService;
import com.yz.mhl.service.MenuService;
import com.yz.mhl.utils.Utility;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings({"all"})
public class MHLView {
    private boolean loop = true;
    private String key = "";

    EmployeeService es = new EmployeeService();
    DiningService ds = new DiningService();
    MenuService ms = new MenuService();
    BillService bs = new BillService();

    public static void main(String[] args) {
        new MHLView().mainMenu();
    }

    // 显示餐桌列表
    public void showDiningList() {
        System.out.println("餐桌编号" + "\t\t" + "餐桌状态");
        List<Dining> dinings = ds.queryDiningList();
        for (Dining dining : dinings) {
            System.out.println(dining);
        }
    }

    // 预定餐桌
    public void bookMeals() {
        System.out.println("============预定餐桌============");
        int diningId = -1;
        while (true) {
            System.out.print("请选择要预定餐桌编号(-1退出): ");
            diningId = Utility.readInt(10);
            if (diningId == -1)
                // 退出预定
                return;
            else {
                // 查询餐桌状态是否为空
                Object status = ds.queryDining(diningId);
                if (status != null) {
                    if (status.equals("空"))
                        break;
                    else if (status.equals("已预定"))
                        System.out.println("该餐桌已预定");
                    else if (status.equals("用餐中"))
                        System.out.println("该餐桌用餐中");
                } else
                    System.out.println("请输入正确的餐桌编号");
            }
        }
        char c = Utility.readConfirmSelection();
        if (c == 'N')
            return;
        System.out.print("预定人名字: ");
        String orderName = Utility.readString(10);
        System.out.print("预定人电话: ");
        String orderTel = Utility.readString(20);
        int rows = ds.updateDining(diningId, orderName, orderTel);
        if (rows > 0)
            System.out.println("============预定成功============");
        else
            System.out.println("============预定失败============");
    }

    // 显示所有菜品
    public void showMenuList() {
        System.out.println("===============显示所有菜品=============");
        System.out.println("菜品编号\t\t菜品名\t\t类别\t\t价格");
        List<Menu> menus = ms.queryMenuList();
        for (Menu menu : menus) {
            System.out.println(menu);
        }
    }

    // 点餐服务
    public void order() {
        System.out.println("===============点餐服务=============");
        System.out.println("请选择点餐的桌号(-1退出): ");
        int diningId = Utility.readInt();
        if (diningId == -1)
            return;
        System.out.println("请选择菜品编号(-1退出): ");
        int menuId = Utility.readInt();
        if (menuId == -1)
            return;
        System.out.println("请选择菜品数量(-1退出): ");
        int menuNums = Utility.readInt();
        if (menuNums == -1)
            return;
        char choose = Utility.readConfirmSelection();
        if (choose == 'Y') {
            // 判断点餐桌号
            String status = (String) ds.queryDining(diningId);
            if (status == null) {
                System.out.println("请输入正确点餐桌号");
                return;
            } else {
                if (status.equals("空")) {
                    System.out.println("该餐桌还未有人预定，不能点餐");
                    return;
                }
            }
            // 判断菜品编号
            Menu menu = ms.queryMenu(menuId);
            if (menu == null) {
                System.out.println("没有这个菜品，请重新选择");
                return;
            }
            // 生成账单
            String billId = UUID.randomUUID().toString();
            Boolean add = bs.addBill(billId, menuId, menuNums, menu.getPrice() * menuNums, diningId);
            // 修改餐桌状态
            boolean update = ds.updateDiningStatus(diningId);
            if (!update || !add) {
                System.out.println("点餐失败");
                return;
            }
            System.out.println("点餐结果: " + menu.getName() + " X " + menuNums);
            System.out.println("===============点餐成功=============");
        }
    }

    public void mainMenu() {
        while (loop) {
            System.out.println("===============满汉楼=============");
            System.out.println("\t\t 1 登录满汉楼");
            System.out.println("\t\t 2 退出满汉楼");
            System.out.print("请输入你的选择：");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    System.out.print("输入员工号：");
                    String empId = Utility.readString(50);
                    System.out.print("输入密码：");
                    String pwd = Utility.readString(50);
                    Employee employee = es.queryUser(empId, pwd);
                    if (employee != null) {
                        System.out.println("=============登录成功 " + employee.getName() + " =============");
                        while (loop) {
                            System.out.println("=============满汉楼(二级菜单)============");
                            System.out.println("\t\t 1 显示餐桌状态");
                            System.out.println("\t\t 2 预定餐桌");
                            System.out.println("\t\t 3 显示所有菜品");
                            System.out.println("\t\t 4 点餐服务");
                            System.out.println("\t\t 5 查看账单");
                            System.out.println("\t\t 6 结账");
                            System.out.println("\t\t 9 退出");
                            System.out.print("输入你的选择：");
                            key = Utility.readString(50);
                            switch (key) {
                                case "1":
                                    showDiningList();
                                    break;
                                case "2":
                                    bookMeals();
                                    break;
                                case "3":
                                    showMenuList();
                                    break;
                                case "4":
                                    order();
                                    break;
                                case "5":
                                    System.out.println("查看账单");
                                    break;
                                case "6":
                                    System.out.println("结账");
                                    break;
                                case "9":
                                    loop = false;
                                    System.out.println("退出成功");
                                    break;
                                default:
                                    System.out.println("你的输入有误，请重新输入");
                            }
                        }
                    } else {
                        System.out.println("登陆失败");
                    }
                    break;
                case "2":
                    loop = false;
                    System.out.println("退出满汉楼");
                    break;
                default:
                    System.out.println("你的输入有误，请重新输入");
            }
        }
    }
}
