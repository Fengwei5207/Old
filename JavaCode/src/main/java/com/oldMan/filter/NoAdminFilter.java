package com.oldMan.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author XiaLuo
 * @version 1.0
 * @date 2024/1/10 10:32
 */
//@WebFilter(urlPatterns = {
//        "/api/addPublicNotice",
//        "/api/agreePreUser",
//        "/api/disagreePreUser",
//        "/api/getAllPreUsers",
//
//})
public class NoAdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String userRole = (String) req.getSession().getAttribute("userRole");
        servletResponse.setCharacterEncoding("UTF-8");
        if (userRole.equals("1")){
            servletResponse.getWriter().write("权限不足,禁止访问!");
        }else if (userRole.equals("2")){
            doFilter(servletRequest,servletResponse,filterChain);
        }


    }

    @Override
    public void destroy() {

    }
}
