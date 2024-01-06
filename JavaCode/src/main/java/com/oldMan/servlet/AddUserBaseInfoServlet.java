package com.oldMan.servlet;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/4 15:38
 */
import com.alibaba.fastjson.JSON;
import com.oldMan.bean.UserBaseInfo;
import com.oldMan.dao.UserBaseInfoDao;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/addUserBaseInfo")
public class AddUserBaseInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // 获取表单数据
        InputStream ism = req.getInputStream();
        String reqBody = IOUtils.toString(ism, "UTF-8");
        UserBaseInfo userBaseInfo = JSON.parseObject(reqBody, UserBaseInfo.class);

        // 将数据添加到数据库
        UserBaseInfoDao userBaseInfoDao = new UserBaseInfoDao();
        boolean success = userBaseInfoDao.addUserBaseInfo(userBaseInfo);

        // 返回结果
        if (success) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

