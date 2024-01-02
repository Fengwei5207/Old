package com.oldMan.dao;
import com.oldMan.bean.User;
import com.oldMan.util.DBUtil;

import java.sql.*;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/2 10:33
 */


public class UserDao {

    // 注册用户
    public boolean registerUser(User user) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO user_info (user_name, password, phone_number, user_role) VALUES (?, ?, ?, ?)";

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getPhoneNumber());
            pstmt.setString(4, user.getUserRole());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeJDBC(connection, pstmt, null);
        }
    }

    public boolean isPhoneExist(String phone) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        String sql = "SELECT * FROM user_info WHERE phone_number = ?";

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, phone);
            res = pstmt.executeQuery();
            return res.next(); // 如果存在结果集，说明电话号码已存在
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeJDBC(connection, pstmt, res);
        }
    }
    public boolean loginUser(String phoneNumber, String password) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        boolean isValidUser = false;

        try {
            connection = DBUtil.getConnection();
            String sql = "SELECT * FROM user_info WHERE phone_number = ? AND password = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, phoneNumber);
            pstmt.setString(2, password);
            resultSet = pstmt.executeQuery();

            isValidUser = resultSet.next(); // 如果有匹配的用户，返回 true
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(connection, pstmt, resultSet);
        }

        return isValidUser;
    }

}
