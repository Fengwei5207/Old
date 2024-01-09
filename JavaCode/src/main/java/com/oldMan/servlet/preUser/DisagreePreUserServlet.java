package com.oldMan.servlet.preUser;

import com.oldMan.dao.PreRegisterUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/8 20:08
 */
@WebServlet("/api/disagreePreUser")
public class DisagreePreUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        PreRegisterUserDao preRegisterUserDao = new PreRegisterUserDao();
        boolean success = preRegisterUserDao.deletePreUser(id);
        if (success){
            resp.getWriter().write("success");
        }
    }
}
