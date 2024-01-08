package com.oldMan.servlet.oldInfo;

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
 * @date 2024/1/7 11:01
 */
@WebServlet("/api/deleteOldInfo")
public class DeleteOldInfoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        int elderlyId = Integer.parseInt(request.getParameter("elderly_id"));

        // 调用DAO删除指定ID的老年人信息
        OldDao oldInfoDao = new OldDao();
        boolean success = oldInfoDao.deleteOldInfo(elderlyId);

        // 处理结果
        if (success) {
            response.getWriter().write("success");
        } else {
            response.getWriter().write("error");
        }
    }
}

