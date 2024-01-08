package com.oldMan.servlet.publicNotice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.oldMan.bean.PublicNotice;
import com.oldMan.dao.PublicNoticeDao;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/3 14:20
 */

@WebServlet("/api/addPublicNotice")
public class AddPublicNoticeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应内容类型为 JSON
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        int noticeType = Integer.parseInt(req.getParameter("noticeType"));

        // 创建公告对象
        PublicNotice notice = new PublicNotice();
        notice.setTitle(title);
        notice.setContent(content);
        notice.setNoticeType(noticeType);

        // 调用 DAO 类中的添加方法
        PublicNoticeDao noticeDao = new PublicNoticeDao();
        boolean isAdded = noticeDao.addPublicNotice(notice);


        if (isAdded) {
//            resp.getWriter().write("success");
            resp.sendRedirect("/publicNotice.html");
        } else {
            resp.getWriter().write("error");
        }

        // 将响应 JSON 写回客户端

    }
}

