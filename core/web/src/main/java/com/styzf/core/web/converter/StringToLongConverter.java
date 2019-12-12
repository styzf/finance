package com.styzf.core.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author yangzf
 */
@Component
public class StringToLongConverter implements Converter<String, Long> {
    
    @Override
    public Long convert(String source) {
        return Long.valueOf(source);
    }
}