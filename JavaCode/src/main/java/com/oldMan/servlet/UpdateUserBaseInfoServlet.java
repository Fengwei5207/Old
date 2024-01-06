package com.oldMan.servlet;

import com.alibaba.fastjson.JSON;
import com.oldMan.bean.UserBaseInfo;
import com.oldMan.dao.UserBaseInfoDao;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/4 15:39
 */
@WebServlet("/api/updateUserBaseInfo")
public class UpdateUserBaseInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String phoneNumber = req.getParameter("phoneNumber");
        String education = req.getParameter("education");
        String intro = req.getParameter("intro");
        Integer userId = Integer.parseInt((String) req.getSession().getAttribute("userId"));


        UserBaseInfoDao userBaseInfoDao = new UserBaseInfoDao();
        boolean success = userBaseInfoDao.updateUserBaseInfo(userId, name, gender, phoneNumber, education, intro);
        if (success){
            resp.sendRedirect("/userInfo.html");
        }else{
            resp.sendRedirect("/editUserInfo.html");
        }
    }
}
