package com.oldMan.dao;

import com.oldMan.bean.User;
import com.oldMan.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/8 19:20
 */
public class PreRegisterUserDao {
    public boolean preRegisterUser(User user) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO pre_user_info (user_name, password, phone_number, user_role) VALUES (?, ?, ?, ?)";

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

    public List<User> getAllPreUserList(){
        List<User> userList= new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from pre_user_info";

        try {
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("user_id"),rs.getString("user_name"), null,rs.getString("phone_number"), rs.getString("user_role"));
                userList.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeJDBC(connection, pstmt,rs);
        }
        return userList;
    }


    public boolean deletePreUser(int userId) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String DELETE_QUERY = "DELETE FROM pre_user_info WHERE user_id = ?";
        try {
            pstmt = connection.prepareStatement(DELETE_QUERY);
            pstmt.setInt(1, userId);

            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeJDBC(connection, pstmt, null);
        }
    }

    public User getPreUserById(int userId) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SELECT_QUERY = "SELECT * FROM pre_user_info WHERE user_id = ?";

        try {
            pstmt = connection.prepareStatement(SELECT_QUERY);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String userName = rs.getString("user_name");
                String password = rs.getString("password");
                String phoneNumber = rs.getString("phone_number");
                String userRole = rs.getString("user_role");

                return new User(userId, userName, password, phoneNumber, userRole);
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeJDBC(connection, pstmt, rs);
        }
    }
}
