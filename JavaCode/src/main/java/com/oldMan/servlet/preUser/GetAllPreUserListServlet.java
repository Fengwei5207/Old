package com.oldMan.servlet.preUser;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/8 19:54
 */
import com.alibaba.fastjson.JSON;
import com.oldMan.bean.User;
import com.oldMan.dao.PreRegisterUserDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/getAllPreUsers")
public class GetAllPreUserListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        PreRegisterUserDao preRegisterUserDao = new PreRegisterUserDao();
        List<User> userList = preRegisterUserDao.getAllPreUserList();

        response.getWriter().write(JSON.toJSONString(userList));
    }
}
