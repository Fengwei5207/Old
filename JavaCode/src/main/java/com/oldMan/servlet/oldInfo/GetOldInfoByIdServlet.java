package com.oldMan.servlet.oldInfo;

import com.alibaba.fastjson.JSON;
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
 * @date 2024/1/8 14:19
 */
@WebServlet("/api/getOldInfoById")
public class GetOldInfoByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));

        OldDao dao = new OldDao();
        Old old = dao.getOldInfoById(id);

        resp.getWriter().write(JSON.toJSONString(old));
    }
}
