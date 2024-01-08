package com.oldMan.servlet.healthDoc;

import com.oldMan.dao.HealthDocDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/8 10:17
 */
@WebServlet("/api/deleteHealthDoc")
public class DeleteHealthDocServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        HealthDocDao healthDocDao = new HealthDocDao();
        boolean success = healthDocDao.deleteHealthData(id);

        if (success){
            resp.getWriter().write("success");
        }
    }
}
