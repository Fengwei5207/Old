package com.oldMan.servlet.user;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/3 10:05
 */
import com.alibaba.fastjson.JSON;
import com.oldMan.bean.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/api/UserInfo")
public class UserInfoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // 如果会话不存在则不创建新会话

        if (session != null && session.getAttribute("userName") != null) {
            // 已登录，显示用户信息
            String userName = (String) session.getAttribute("userName");
            String phoneNumber = (String) session.getAttribute("phoneNumber");
            String userRole = (String) session.getAttribute("userRole");
            String userId = (String) session.getAttribute("userId");

            String userJson = "{";
            userJson += "\"userName\":\"" + userName + "\",";
            userJson += "\"phoneNumber\":\"" + phoneNumber + "\",";
            userJson += "\"userId\":\"" + userId + "\",";
            userJson += "\"userRole\":\"" + userRole + "\"";
            userJson += "}";

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(userJson);
        } else {
            // 未登录，重定向到登录页面
            response.getWriter().println("unLogin");
        }
    }
}

