package com.bjpowernode.controller;

import com.bjpowernode.pojo.Admin;
import com.bjpowernode.service.adminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// HttpServletResponse response
@Controller
@RequestMapping("/admin")
public class admincontroler {
    @Resource
    private adminService   adm;
    @RequestMapping("/login")
    public String seekuser(String name, String pwd, String sign, HttpServletRequest request
    , HttpServletResponse  response)
    {
        Admin ad= new Admin(name, pwd);
        Admin seekuser = adm.seekuser(ad);
        if(seekuser!=null)
        {
            request.setAttribute("admin",seekuser);
            HttpSession session = request.getSession();
            session.setAttribute("user",seekuser);
            if("1".equals(sign))
            {
                Cookie cookie = new Cookie("name",name);
                Cookie cookie1 = new Cookie("pwd",pwd);
                cookie.setMaxAge(60*10*24*10);
                cookie1.setMaxAge(60*10*24*10);
                response.addCookie(cookie);
                response.addCookie(cookie1);
            }
           return "main";
        } else{
            request.setAttribute("errmsg","用户名或密码错误!");
            return "login";
        }
    }
}
