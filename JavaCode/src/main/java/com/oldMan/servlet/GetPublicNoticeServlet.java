package com.oldMan.servlet;

import com.alibaba.fastjson.JSON;
import com.oldMan.bean.PublicNotice;
import com.oldMan.dao.PublicNoticeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/3 14:06
 */
@WebServlet("/api/getPublicNotice")
public class GetPublicNoticeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PublicNoticeDao noticeDao = new PublicNoticeDao();
        List<PublicNotice> notices = noticeDao.getAllPublicNotice();

        String json = JSON.toJSONString(notices);

        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }
}
