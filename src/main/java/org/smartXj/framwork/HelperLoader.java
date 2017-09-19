package org.smartXj.framwork;

import org.smartXj.framwork.helper.BeanHelper;
import org.smartXj.framwork.helper.ClassHelper;
import org.smartXj.framwork.helper.ControllerHelper;
import org.smartXj.framwork.helper.IocHelper;
import org.smartXj.framwork.util.ClassUtil;

public class HelperLoader {
    public static  void init()
    {
        Class<?>[] classList={
                ClassHelper.class,
                BeanHelper.class,
                ControllerHelper.class,
                IocHelper.class
        };

        for (Class cls:classList)
        {
            ClassUtil.loadClass(cls.getName(),true);
        }
    }


}
