package com.styzf.mybatis.util;

import tk.mybatis.mapper.weekend.Fn;
import tk.mybatis.mapper.weekend.reflection.Reflections;

/**
 * 用于获取字段名的工具类
 * @author yangzf
 * @date 2019-09-02
 */
public class FnUtil {
	

	public static <T, R> String getFieldName(Fn<T, R> fn) {
        return Reflections.fnToFieldName(fn);
    }
    
	@SafeVarargs
	public static <T, R> String[] getFieldNames(Fn<T, R> ... fns) {
		if (fns == null || fns.length == 0) {
			return null;
		}
    	
    	String[] fieldNames = new String[fns.length];
    	for (int i = 0, length = fieldNames.length; i < length; i++) {
    		String fieldName = Reflections.fnToFieldName(fns[i]);
    		fieldNames[i] = fieldName;
		}
        return fieldNames;
    }
    
}
