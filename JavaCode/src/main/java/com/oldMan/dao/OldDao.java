package com.oldMan.dao;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/7 10:59
 */
import com.oldMan.bean.Old;
import com.oldMan.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OldDao {

    public boolean addOldInfo(Old oldInfo) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String INSERT_QUERY = "INSERT INTO old_info (name, gender, birthdate, address, health_condition, medication_info) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            pstmt = connection.prepareStatement(INSERT_QUERY);
            pstmt.setString(1, oldInfo.getName());
            pstmt.setString(2, oldInfo.getGender());
            pstmt.setString(3, oldInfo.getBirthdate());
            pstmt.setString(4, oldInfo.getAddress());
            pstmt.setString(5, oldInfo.getHealthCondition());
            pstmt.setString(6, oldInfo.getMedicationInfo());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeJDBC(connection, pstmt,null);
        }
    }

    public boolean deleteOldInfo(int oldId) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String DELETE_QUERY = "DELETE FROM old_info WHERE elderly_id = ?";
        try {
            pstmt = connection.prepareStatement(DELETE_QUERY);
            pstmt.setInt(1, oldId);

            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeJDBC(connection, pstmt,null);
        }
    }

    public boolean updateOldInfo(Old oldInfo) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String UPDATE_QUERY = "UPDATE old_info SET name=?, gender=?, birthdate=?, address=?, health_condition=?, medication_info=? WHERE elderly_id=?";
        try {
            pstmt = connection.prepareStatement(UPDATE_QUERY);
            pstmt.setString(1, oldInfo.getName());
            pstmt.setString(2, oldInfo.getGender());
            pstmt.setString(3, oldInfo.getBirthdate());
            pstmt.setString(4, oldInfo.getAddress());
            pstmt.setString(5, oldInfo.getHealthCondition());
            pstmt.setString(6, oldInfo.getMedicationInfo());
            pstmt.setInt(7, oldInfo.getElderlyID());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeJDBC(connection, pstmt,null);
        }
    }

    public List<Old> getAllOldInfo() {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Old> oldList = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM old_info";
        try {
            pstmt = connection.prepareStatement(SELECT_QUERY);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Old old = new Old();
                old.setElderlyID(rs.getInt("elderly_id"));
                old.setName(rs.getString("name"));
                old.setGender(rs.getString("gender"));
                old.setBirthdate(rs.getString("birthdate"));
                old.setAddress(rs.getString("address"));
                old.setHealthCondition(rs.getString("health_condition"));
                old.setMedicationInfo(rs.getString("medication_info"));

                oldList.add(old);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeJDBC(connection, pstmt,rs);
        }
        return oldList;
    }

    public Old getOldInfoById(int id) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Old old = new Old();
        String SELECT_QUERY = "SELECT * FROM old_info where elderly_id = ?";
        try {
            pstmt = connection.prepareStatement(SELECT_QUERY);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                old.setElderlyID(rs.getInt("elderly_id"));
                old.setName(rs.getString("name"));
                old.setGender(rs.getString("gender"));
                old.setBirthdate(rs.getString("birthdate"));
                old.setAddress(rs.getString("address"));
                old.setHealthCondition(rs.getString("health_condition"));
                old.setMedicationInfo(rs.getString("medication_info"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeJDBC(connection, pstmt,rs);
        }
        return old;
    }


}
