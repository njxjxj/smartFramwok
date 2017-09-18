package org.smartXj.framwork;

import org.smartXj.framwork.bean.Handler;
import org.smartXj.framwork.bean.Param;
import org.smartXj.framwork.helper.BeanHelper;
import org.smartXj.framwork.helper.ConfigHelper;
import org.smartXj.framwork.helper.ControllerHelper;
import org.smartXj.framwork.util.CodecUtil;
import org.smartXj.framwork.util.StreamUtil;
import org.smartXj.framwork.util.StringUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //初始化框架类的加载
        HelperLoader.init();
        //获取servlet上下文
        ServletContext servletContext = servletConfig.getServletContext();
        //注册处理JSP的服务
        ServletRegistration jspSrvice = servletContext.getServletRegistration("jsp");
        jspSrvice.addMapping(ConfigHelper.getAppJspPath() + "*");
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求方法与请求路径
        String requestMethod = req.getMethod();
        String requestPath = req.getPathInfo();
        //获取action处理器
        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (handler != null) {
            //获取controller以及bean实例
            Class controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);
            //创建请求对象的参数
            Map<String, Object> paramMap = new HashMap<String, Object>();
            Enumeration<String> paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String parmaName = paramNames.nextElement();
                String parmaValue = req.getParameter(parmaName);
                paramMap.put(parmaName, parmaValue);
            }
            String  body= CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));
            Param param = new Param(paramMap);
            if(StringUtil.isNotEmpty(body))
            {
                String[] params=StringUtil.splitString(body,"&");
            }

        }


    }
}
