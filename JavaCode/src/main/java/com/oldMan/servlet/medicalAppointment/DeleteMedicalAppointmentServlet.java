package com.oldMan.servlet.medicalAppointment;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/9 19:18
 */
import com.oldMan.dao.MedicalAppointmentDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/deleteMedicalAppointment")
public class DeleteMedicalAppointmentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int appointmentId = Integer.parseInt(request.getParameter("id"));

        MedicalAppointmentDAO appointmentDAO = new MedicalAppointmentDAO();
        boolean success = appointmentDAO.deleteMedicalAppointment(appointmentId);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(success ? "success" : "fail");
    }
}

