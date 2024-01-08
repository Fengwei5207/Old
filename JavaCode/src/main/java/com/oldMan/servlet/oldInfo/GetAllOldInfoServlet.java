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
import java.util.List;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/7 11:02
 */
@WebServlet("/api/getAllOldInfo")
public class GetAllOldInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        OldDao oldDao = new OldDao();
        List<Old> oldInfoList = oldDao.getAllOldInfo();
        String json = JSON.toJSONString(oldInfoList);

        resp.getWriter().write(json);
    }
}
