package org.smartXj.framwork;

import org.smartXj.framwork.bean.Handler;
import org.smartXj.framwork.bean.Param;
import org.smartXj.framwork.bean.View;
import org.smartXj.framwork.helper.BeanHelper;
import org.smartXj.framwork.helper.ConfigHelper;
import org.smartXj.framwork.helper.ControllerHelper;
import org.smartXj.framwork.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
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
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求方法与请求路径
        String requestMethod = req.getMethod();
        String requestPath = req.getPathInfo();
        //获取action句柄
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
            String body = CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));


            //针对GET提交的参数
            if (StringUtil.isNotEmpty(body)) {
                String[] params = StringUtil.splitString(body, "&");
                if (ArrayUtil.isNotEmpty(params)) {
                    for (String param : params) {
                        String[] array = StringUtil.splitString(param, "=");
                        if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                            String paramName = array[0];
                            String paramValue = array[1];
                            paramMap.put(paramName, paramValue);
                        }
                    }
                }
            }
            Param param = new Param(paramMap);
            Method actionMethod = handler.getActionMethod();
            //使用反射运行方法
            Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod,param);
            if (result instanceof View) {
                View view = (View) result;
                String path = view.getPath();
                if (path.startsWith("/")) {
                    resp.sendRedirect(req.getContextPath() + path);
                } else {
                    Map<String, Object> model = view.getModel();
                    for (Map.Entry<String, Object> entry : model.entrySet()) {
                        req.setAttribute(entry.getKey(), entry.getValue());
                    }
                    req.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(req, resp);
                }
            }


        }


    }
}
