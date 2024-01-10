package com.oldMan.servlet.medicalAppointment;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/9 19:16
 */
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.oldMan.bean.MedicalAppointment;
import com.oldMan.dao.MedicalAppointmentDAO;

@WebServlet("/api/getAllMedicalAppointments")
public class GetAllMedicalAppointmentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MedicalAppointmentDAO appointmentDAO = new MedicalAppointmentDAO();
        List<MedicalAppointment> appointments = appointmentDAO.getAllMedicalAppointments();

        String json = JSON.toJSONString(appointments);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}

