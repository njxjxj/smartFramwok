package org.smartXj.framwork.helper;

import org.smartXj.framwork.annotation.Action;
import org.smartXj.framwork.bean.Handler;
import org.smartXj.framwork.bean.Request;
import org.smartXj.framwork.util.ArrayUtil;
import org.smartXj.framwork.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ControllerHelper {

    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {
        System.out.println("---------------Controller初始化-----------------");
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)) {
            for (Class<?> cls : controllerClassSet) {
                Method[] methods = cls.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)) {
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(Action.class)) {
                            Action action = method.getAnnotation(Action.class);
                            String mpping = action.value();
                            if (mpping.matches("\\w+:/\\w*")) {
                                String[] array = mpping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(cls, method);
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    public static Handler getHandler(String requestMethods, String requestPath) {
        Request request = new Request(requestMethods, requestPath);
        return ACTION_MAP.get(request);
    }
}
