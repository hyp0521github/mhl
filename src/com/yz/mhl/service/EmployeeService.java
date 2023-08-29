package com.yz.mhl.service;

import com.yz.mhl.dao.EmployeeDao;
import com.yz.mhl.domain.Employee;

/**
 * @author 院长
 * @version 1.0.0
 */
public class EmployeeService {
    private EmployeeDao employeeDao = new EmployeeDao();

    // 查询用户
    public Employee queryUser(String empId, String pwd) {
        String sql = "select * from employee where empId = ? and pwd = md5(?)";
        return employeeDao.querySingle(sql, Employee.class, empId, pwd);
    }
}
