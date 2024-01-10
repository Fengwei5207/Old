package com.oldMan.servlet.healthDoc;

import com.oldMan.bean.HealthData;
import com.oldMan.dao.HealthDocDao;
import com.oldMan.dao.OldDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/8 10:08
 */
@WebServlet("/api/addHealthDoc")
public class AddHealthDocServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        int elderlyId = Integer.parseInt(req.getParameter("elderlyId"));
        int heartRate = Integer.parseInt(req.getParameter("heartRate"));
        String bloodPressure = req.getParameter("bloodPressure");
        int activeLevel = Integer.parseInt(req.getParameter("activeLevel"));
        int sleepQuality = Integer.parseInt(req.getParameter("sleepQuality"));
//        int elderlyId = req.getParameter("elderlyId");
//        int heartRate = req.getParameter("heartRate");
//        String bloodPressure = req.getParameter("bloodPressure");
//        int activeLevel = req.getParameter("activeLevel");
//        int sleepQuality = req.getParameter("sleepQuality");

        HealthData healthData = new HealthData(-1, elderlyId, null, heartRate,bloodPressure,activeLevel, sleepQuality);
        HealthDocDao healthDocDao = new HealthDocDao();
        boolean success =  healthDocDao.addHealthData(healthData);

        if (success){
            resp.sendRedirect("/healthDoc.html");
        }

    }
}
