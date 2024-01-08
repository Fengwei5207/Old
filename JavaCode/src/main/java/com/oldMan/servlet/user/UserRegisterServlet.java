package com.oldMan.servlet.user;

import com.oldMan.bean.User;
import com.oldMan.dao.PreRegisterUserDao;
import com.oldMan.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/2 10:32
 */


@WebServlet("/api/register")
public class UserRegisterServlet extends HttpServlet {
    private UserDao userDao = new UserDao(); // UserDao实例，用于处理用户相关操作

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");
        String userRole = request.getParameter("userRole");

        // 创建用户对象
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setUserRole(userRole);
        // 调用UserDao的方法进行用户注册
        boolean isExist = userDao.isPhoneExist(user.getPhoneNumber());
        if (isExist) {
//            response.getWriter().println("当前电话号码已被注册");
            response.sendRedirect("/registry.html?error=isExist");
        } else {
            boolean success = false;
            //            根据注册的角色选择不同的注册方式
            if (Objects.equals(user.getUserRole(), "1")){
//              护工
                PreRegisterUserDao preRegisterUserDao = new PreRegisterUserDao();
                success = preRegisterUserDao.preRegisterUser(user);

            } else if (Objects.equals(user.getUserRole(), "2")) {
//              管理员
                success = userDao.registerUser(user);
            }

            if (success) {
//            response.getWriter().write("success"); // 注册成功
                response.sendRedirect("/login.html"); // 注册成功后重定向到登录页面
            } else {
//            response.getWriter().write("error"); // 注册失败\]
//            通过js生成提示框
                response.getWriter().write("/registry.html?error=invalid");
            }
        }



    }
}
