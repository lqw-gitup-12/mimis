package com.bjpowernode;

import com.bjpowernode.pojo.ProductType;
import com.bjpowernode.service.impl.adminServiceimpl;
import com.bjpowernode.service.impl.productypeServiceimpl;
import com.bjpowernode.service.producttypeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;
@WebListener
public class listern implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent scet) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_*.xml");
        producttypeService  bean= (producttypeService) context.getBean("protype");
        List<ProductType> lists = bean.selectalltype();
        ServletContext sc = scet.getServletContext();
        sc.setAttribute("ptlist",lists);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
