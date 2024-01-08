package com.oldMan.dao;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/8 08:42
 */
import com.oldMan.bean.HealthData;
import com.oldMan.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HealthDocDao {

    // 新增健康数据
    public boolean addHealthData(HealthData healthData) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String INSERT_QUERY = "INSERT INTO health_data (elderly_id, heart_rate, blood_pressure, activity_level, sleep_quality) VALUES (?, ?, ?, ?, ?)";
        try {
            pstmt = connection.prepareStatement(INSERT_QUERY);
            pstmt.setInt(1, healthData.getElderlyID());
            pstmt.setInt(2, healthData.getHeartRate());
            pstmt.setString(3, healthData.getBloodPressure());
            pstmt.setInt(4, healthData.getActivityLevel());
            pstmt.setInt(5, healthData.getSleepQuality());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 删除健康数据
    public boolean deleteHealthData(int healthId) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String DELETE_QUERY = "DELETE FROM health_data WHERE health_id = ?";
        try {
            pstmt = connection.prepareStatement(DELETE_QUERY);
            pstmt.setInt(1, healthId);

            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 查询特定老人的健康数据列表
    public List<HealthData> getHealthDataByElderlyId(int elderlyId) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<HealthData> healthDataList = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM health_data WHERE elderly_id = ?";
        try {
            pstmt = connection.prepareStatement(SELECT_QUERY);
            pstmt.setInt(1, elderlyId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                HealthData healthData = new HealthData();
                healthData.setHealthID(rs.getInt("health_id"));
                healthData.setElderlyID(rs.getInt("elderly_id"));
                healthData.setHeartRate(rs.getInt("heart_rate"));
                healthData.setBloodPressure(rs.getString("blood_pressure"));
                healthData.setActivityLevel(rs.getInt("activity_level"));
                healthData.setSleepQuality(rs.getInt("sleep_quality"));
                healthDataList.add(healthData);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return healthDataList;
    }

    public List<HealthData> getAllHealthData(){
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<HealthData> healthDataList = new ArrayList<>();

        String sql = "select * from health_data";
        try {
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                HealthData healthData = new HealthData();
                healthData.setHealthID(rs.getInt("health_id"));
                healthData.setElderlyID(rs.getInt("elderly_id"));
                healthData.setHeartRate(rs.getInt("heart_rate"));
                healthData.setBloodPressure(rs.getString("blood_pressure"));
                healthData.setActivityLevel(rs.getInt("activity_level"));
                healthData.setSleepQuality(rs.getInt("sleep_quality"));

                OldDao oldDao = new OldDao();
                healthData.setName(oldDao.getOldInfoById(healthData.getElderlyID()).getName());

                healthDataList.add(healthData);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return healthDataList;
    }
}
