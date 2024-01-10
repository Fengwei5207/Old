package com.oldMan.dao;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/9 19:14
 */
import com.oldMan.bean.MedicalAppointment;
import com.oldMan.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MedicalAppointmentDAO {

    // 添加医疗预约
    public boolean addMedicalAppointment(MedicalAppointment appointment) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String INSERT_QUERY = "INSERT INTO medical_appointment (old_id, title, start_date_time, context) VALUES (?, ?, ?, ?)";
        try {
            pstmt = connection.prepareStatement(INSERT_QUERY);
            pstmt.setInt(1, appointment.getOldId());
            pstmt.setString(2, appointment.getTitle());
            pstmt.setObject(3, appointment.getStartDateTime());
            pstmt.setString(4, appointment.getContext());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeJDBC(connection, pstmt,null);
        }
    }

    // 删除医疗预约
    public boolean deleteMedicalAppointment(int medicalAppointmentId) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String DELETE_QUERY = "DELETE FROM medical_appointment WHERE medical_appointment_id = ?";
        try {
            pstmt = connection.prepareStatement(DELETE_QUERY);
            pstmt.setInt(1, medicalAppointmentId);

            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeJDBC(connection, pstmt,null);
        }
    }

    // 根据ID查询医疗预约
    public MedicalAppointment getMedicalAppointmentById(int medicalAppointmentId) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SELECT_QUERY = "SELECT * FROM medical_appointment WHERE medical_appointment_id = ?";
        try {
            pstmt = connection.prepareStatement(SELECT_QUERY);
            pstmt.setInt(1, medicalAppointmentId);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                MedicalAppointment appointment = new MedicalAppointment();
                appointment.setMedicalAppointmentId(rs.getInt("medical_appointment_id"));
                appointment.setOldId(rs.getInt("old_id"));
                appointment.setTitle(rs.getString("title"));
                appointment.setStartDateTime(rs.getObject("start_date_time", LocalDateTime.class));
                appointment.setContext(rs.getString("context"));
                return appointment;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeJDBC(connection, pstmt,rs);
        }
        return null;
    }

    // 查询所有医疗预约
    public List<MedicalAppointment> getAllMedicalAppointments() {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SELECT_ALL_QUERY = "SELECT * FROM medical_appointment";
        List<MedicalAppointment> appointments = new ArrayList<>();
        try {
            pstmt = connection.prepareStatement(SELECT_ALL_QUERY);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                MedicalAppointment appointment = new MedicalAppointment();
                appointment.setMedicalAppointmentId(rs.getInt("medical_appointment_id"));
                appointment.setOldId(rs.getInt("old_id"));
                appointment.setTitle(rs.getString("title"));
                appointment.setStartDateTime(rs.getObject("start_date_time", LocalDateTime.class));
                appointment.setContext(rs.getString("context"));
                appointments.add(appointment);
            }
            return appointments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeJDBC(connection, pstmt, rs);
        }
    }
}

