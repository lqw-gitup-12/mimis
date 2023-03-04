package com.bjpowernode;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest Request, ServletResponse Response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest  res= (HttpServletRequest) Request;
        HttpServletResponse  rep= (HttpServletResponse) Response;
        Request.setCharacterEncoding("utf-8");// 请求
        Response.setCharacterEncoding("utf-8");// 响应
        Response.setContentType("text/html;charset=utf-8");// 响应

        StringBuffer  URL = res.getRequestURL();
        String s = URL.toString();
        String a []={"/admin/login.action","/css/","/image*/","/js/","/fonts/","/admin/login.jsp"};
        for(String s1:a)
        {
            if(s.contains(s1))
            {
                chain.doFilter(res,rep);
                return;
            }
        }

        HttpSession session = res.getSession();
        Object user = session.getAttribute("user");
        System.out.println(user);
        if(user!=null)
        {
            chain.doFilter(res,rep);
            return;
        }else {
            res.getRequestDispatcher("/admin/login.jsp").forward(res,rep);
        }

    }

    @Override
    public void destroy() {

    }
}
