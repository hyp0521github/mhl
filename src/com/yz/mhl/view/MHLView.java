package com.yz.mhl.view;

import com.yz.mhl.utils.JDBCUtilsByDruid;
import com.yz.mhl.utils.Utility;

import java.sql.Connection;

/**
 * @author 院长
 * @version 1.0.0
 */
public class MHLView {
    public static void main(String[] args) {
        System.out.println("请输入");
        String s = Utility.readString(10);

        Connection connection = JDBCUtilsByDruid.getConnection();
        System.out.println(connection);
    }
}
