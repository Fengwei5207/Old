package com.oldMan.servlet.healthDoc;

import com.alibaba.fastjson.JSON;
import com.oldMan.bean.HealthData;
import com.oldMan.dao.HealthDocDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/8 10:04
 */
@WebServlet("/api/getAllHealthDoc")
public class GetAllHealthDocServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        HealthDocDao dao = new HealthDocDao();
        List<HealthData>  healthDataList = dao.getAllHealthData();

        String json = JSON.toJSONString(healthDataList);

        resp.getWriter().write(json);
    }
}
