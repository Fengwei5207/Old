package com.oldMan.servlet.medicalAppointment;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/9 19:17
 */

import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oldMan.bean.MedicalAppointment;
import com.oldMan.dao.MedicalAppointmentDAO;
import com.oldMan.dao.OldDao;

@WebServlet("/api/addMedicalAppointment")
public class AddMedicalAppointmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int oldId = Integer.parseInt(request.getParameter("oldId"));
        String title = request.getParameter("title");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("startDateTime"));
        String context = request.getParameter("context");

        OldDao oldDao = new OldDao();



        MedicalAppointment appointment = new MedicalAppointment(-1,oldId,oldDao.getOldInfoById(oldId).getName()+"的"+title,dateTime,context);

        MedicalAppointmentDAO appointmentDAO = new MedicalAppointmentDAO();
        boolean success = appointmentDAO.addMedicalAppointment(appointment);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.sendRedirect("/MedicalAppointment.html");
//        response.getWriter().write(success ? "success" : "fail");
    }
}

