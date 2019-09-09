package com.styzf.core.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

/**
 * 简单的转换工具类
 * @author styzf
 * @date 2018年7月27日 
 *
 */
@Slf4j
public class ConvertUtil {
    
    public static <S, D> D convert(S source, Class<D> clazz) {
        D object = null;
        try {
            object = clazz.newInstance();
            BeanUtils.copyProperties(source, object);
        } catch (Exception e) {
            log.error(JSON.toJSONString(e.getStackTrace()));
        }
        return object;
    }
    
    public static <S, D> List<D> convertList(Iterable<S> sourceList, Class<D> clazz) {
        List<D> list = new ArrayList<>();
        if (sourceList == null) {
            return list;
        }
        sourceList.forEach(data -> {
            D d = convert(data, clazz);
            if (d != null) {
                list.add(d);
            }
        });
        return list;
    }
    
    public static Map<String, Object> transBean2Map(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(obj.getClass());
        } catch (IntrospectionException e) {
            log.error(JSON.toJSONString(e.getStackTrace()));
            return null;
        }
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            
            if (!key.equals("class"))  {
                Method getter = property.getReadMethod();
                Object value = null;
                try {
                    value = getter.invoke(obj, new Object[0]);
                    map.put(key, value);
                } catch (Exception e) {
                    log.error(JSON.toJSONString(e.getStackTrace()));
                }
            }
        }
        
        return map;
    }
}
