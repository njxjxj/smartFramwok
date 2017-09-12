package org.smartXj.framwork.helper;

import org.apache.commons.lang3.ArrayUtils;
import org.smartXj.framwork.annotation.Inject;
import org.smartXj.framwork.util.ArrayUtil;
import org.smartXj.framwork.util.CollectionUtil;
import org.smartXj.framwork.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

public class IocHelper {
    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                Class beanEntryKey = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                //获取Bean的所有成员变量
                Field[] beanFields = beanEntryKey.getFields();
                if (ArrayUtil.isNotEmpty(beanFields)) {
                    for (Field field : beanFields) {
                        //判断是否带有Inject注解
                        if (field.isAnnotationPresent(Inject.class)) {
                            Class<?> beanFieldClass = field.getType();
                            Object beanFieldObj= beanMap.get(beanFieldClass);
                            if(beanFieldObj!=null)
                            {
                                ReflectionUtil.setField(beanInstance,field,beanFieldObj);
                            }
                        }
                    }
                }
            }
        }
    }
}
