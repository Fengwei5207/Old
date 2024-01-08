package com.oldMan.servlet;

import com.oldMan.bean.Old;
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
 * @date 2024/1/7 11:00
 */
@WebServlet("/api/updateOldInfo")
public class UpdateOldInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取表单数据和要修改的老年人 ID
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        int elderlyId = Integer.parseInt(request.getParameter("elderly_id"));
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String birthdate = request.getParameter("birthdate");
        String address = request.getParameter("address");
        String healthCondition = request.getParameter("health_condition");
        String medicationInfo = request.getParameter("medication_info");

        // 创建OldInfo对象
        Old old = new Old(elderlyId, name, gender, birthdate, address, healthCondition, medicationInfo);

        // 调用DAO将OldInfo对象更新到数据库
        OldDao oldInfoDao = new OldDao();
        boolean success = oldInfoDao.updateOldInfo(old);

        // 处理结果
        if (success) {
            response.sendRedirect("/oldList.html");
        } else {
            response.sendRedirect("/oldList.html");
        }
    }
}

