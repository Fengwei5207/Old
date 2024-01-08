package com.oldMan.servlet.user;

import com.alibaba.fastjson.JSON;
import com.oldMan.bean.UserBaseInfo;
import com.oldMan.dao.UserBaseInfoDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/4 15:25
 */
@WebServlet("/api/getUserBaseInfo")
public class getUserBaseInfoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int userId = Integer.parseInt(request.getParameter("userId")); // Assuming userId is passed as a parameter

        UserBaseInfoDao userBaseInfoDao = new UserBaseInfoDao();
        UserBaseInfo userBaseInfo = userBaseInfoDao.getUserBaseInfo(userId);

        String json = JSON.toJSONString(userBaseInfo);

        response.getWriter().write(json);
    }

}
