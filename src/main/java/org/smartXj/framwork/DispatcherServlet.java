package org.smartXj.framwork;

import org.smartXj.framwork.helper.ConfigHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;

public class DispatcherServlet extends HttpServlet{
    @Override
    public void init(ServletConfig servletConfig) throws ServletException
    {
        //初始化框架类的加载
        HelperLoader.init();
        //获取servlet上下文
        ServletContext servletContext=servletConfig.getServletContext();
        //注册处理JSP的服务
        ServletRegistration jspSrvice=servletContext.getServletRegistration("jsp");
        jspSrvice.addMapping(ConfigHelper.getAppJspPath()+"*");
        ServletRegistration defaultServlet=servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath()+"*");
    }
}
