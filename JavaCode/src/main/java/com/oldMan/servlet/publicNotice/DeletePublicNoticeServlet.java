package com.oldMan.servlet.publicNotice;

import com.oldMan.dao.PublicNoticeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/3 14:21
 */

@WebServlet("/api/deletePublicNotice")
public class DeletePublicNoticeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取需要删除的公告ID参数
        String noticeIdString = req.getParameter("noticeId");

        if (noticeIdString != null && !noticeIdString.isEmpty()) {
            try {
                int noticeId = Integer.parseInt(noticeIdString);

                // 调用 DAO 类中的删除方法
                PublicNoticeDao noticeDao = new PublicNoticeDao();
                boolean isDeleted = noticeDao.deletePublicNotice(noticeId);

                if (isDeleted) {
                    // 删除成功
                    resp.sendRedirect("/publicNotice.html");
                    resp.getWriter().write("success");
                } else {
                    // 删除失败
                    resp.getWriter().write("error");
                }
            } catch (NumberFormatException e) {
                // 处理数字格式异常
                resp.getWriter().write("error");
            }
        } else {
            // 参数错误
            resp.getWriter().write("error");
        }
    }
}
