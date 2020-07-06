package com.styzf.core.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author styzf
 * @date 2020-07-03
 */
@Slf4j
public class ObjectUtil extends ObjectUtils {
    
    /**
     * 创建实体类
     * @param clazz
     * @return
     */
    public static  <T> T newTclass(Class<T> clazz) {
        try {
            T a = clazz.newInstance();
            return a;
        } catch (Exception e) {
            log.error("创建实体类失败：{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
