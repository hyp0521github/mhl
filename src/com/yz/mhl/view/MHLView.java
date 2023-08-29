package com.yz.mhl.view;


import com.yz.mhl.domain.Dining;
import com.yz.mhl.domain.Employee;
import com.yz.mhl.service.DiningService;
import com.yz.mhl.service.EmployeeService;
import com.yz.mhl.utils.Utility;

import java.util.List;

/**
 * @author 院长
 * @version 1.0.0
 */
@SuppressWarnings({"all"})
public class MHLView {
    private boolean loop = true;
    private String key = "";

    EmployeeService es = new EmployeeService();
    DiningService ds  = new DiningService();

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

    public void mainMenu() {
        while (loop) {
            System.out.println("===============满汉楼============");
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
                                    System.out.println("预定餐桌");
                                    break;
                                case "3":
                                    System.out.println("显示所有菜品");
                                    break;
                                case "4":
                                    System.out.println("点餐服务");
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
