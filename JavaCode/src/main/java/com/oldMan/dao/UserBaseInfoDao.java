package com.oldMan.dao;

import com.oldMan.bean.UserBaseInfo;
import com.oldMan.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/4 15:30
 */


public class UserBaseInfoDao {

    // 获取用户基本信息
    public UserBaseInfo getUserBaseInfo(int userId) {
        UserBaseInfo userBaseInfo = null;

        // 与数据库建立连接，执行查询操作，将查询结果转换为 UserBaseInfo 对象
        try {
            Connection connection = DBUtil.getConnection();
            String sql = "SELECT * FROM user_base_info WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                userBaseInfo = new UserBaseInfo();
                userBaseInfo.setUserId(resultSet.getInt("user_id"));
                userBaseInfo.setGender(resultSet.getString("gender"));
                userBaseInfo.setEducation(resultSet.getString("education"));
                userBaseInfo.setIntro(resultSet.getString("intro"));
            }

            resultSet.close();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }

        return userBaseInfo;
    }

    // 更新用户基本信息
    public boolean updateUserBaseInfo(Integer userId, String name, String gender, String phoneNumber, String education, String intro) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement pstmt = null;
        String sqlUserInfo = "UPDATE user_info SET user_name=?, phone_number=? WHERE user_id=?";
        String sqlUserBaseInfo = "UPDATE user_base_info SET gender=?, education=?, intro=? WHERE user_id=?";

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false); // Start transaction

            // Update user_info table
            pstmt = connection.prepareStatement(sqlUserInfo);
            pstmt.setString(1, name);
            pstmt.setString(2, phoneNumber);
            pstmt.setInt(3, userId);
            int rowsAffectedUserInfo = pstmt.executeUpdate();
            pstmt.close();

            // Update user_base_info table
            pstmt = connection.prepareStatement(sqlUserBaseInfo);
//            pstmt.setString(1, name);
            pstmt.setString(1, gender);
//            pstmt.setString(3, phoneNumber);
            pstmt.setString(2, education);
            pstmt.setString(3, intro);
            pstmt.setInt(4, userId);
            int rowsAffectedUserBaseInfo = pstmt.executeUpdate();
            pstmt.close();

            // Commit transaction if both updates were successful
            if (rowsAffectedUserInfo > 0 && rowsAffectedUserBaseInfo > 0) {
                connection.commit();
                success = true;
            } else {
                connection.rollback(); // Rollback if any update failed
            }
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback(); // Rollback if any exception occurred
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true); // Reset auto-commit to default behavior
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return success;
    }
    public boolean addUserBaseInfo(UserBaseInfo userBaseInfo) {
        boolean success = false;

        try {
            Connection connection = DBUtil.getConnection();
            String sql = "INSERT INTO user_base_info (user_id, gender, education, intro) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, userBaseInfo.getUserId());
            pstmt.setString(2, userBaseInfo.getGender());
            pstmt.setString(3, userBaseInfo.getEducation());
            pstmt.setString(4, userBaseInfo.getIntro());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                success = true; // 表示新增成功
            }

            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理异常
        }

        return success;
    }

}

