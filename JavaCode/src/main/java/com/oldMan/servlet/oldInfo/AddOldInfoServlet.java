package com.oldMan.servlet.oldInfo;

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
 * @date 2024/1/7 10:57
 */
@WebServlet("/api/addOldInfo")
public class AddOldInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");


        // 获取表单数据
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String birthdate = request.getParameter("birthdate");
        String address = request.getParameter("address");
        String healthCondition = request.getParameter("health_condition");
        String medicationInfo = request.getParameter("medication_info");

        // 创建OldInfo对象
        Old old = new Old(-1,name, gender, birthdate, address, healthCondition, medicationInfo);

        // 调用DAO将OldInfo对象插入到数据库
        OldDao oldDao = new OldDao();
        boolean success = oldDao.addOldInfo(old);

        // 处理结果
        if (success) {
            response.sendRedirect("/oldList.html");
        } else {
            response.sendRedirect("/addOld.html");
        }
    }
}

