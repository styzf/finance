package com.styzf.core.web.converter;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.util.ISO8601Utils;
import com.styzf.core.common.constant.CommonConstant;

/**
 * @author yangzf
 */
@Component
public class StringToDateConverter implements Converter<String, Date> {
    
    /**
     * 多个格式以;号切割
     */
    @Value("${styzf.dateConverter.date-format}")
    private String dateFormat;
    
    private String[] dateFormats;
    
    public Date convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        source = source.trim();
        try {
            if (source.matches("^\\d+$")) {
                Long lDate = new Long(source);
                return new Date(lDate.longValue());
            }
            Date date;
            SimpleDateFormat formatter;
            if (dateFormats == null) {
                dateFormats = dateFormat.split(CommonConstant.SEMICOLON);
            }
            for (String pattern : dateFormats) {
                formatter = new SimpleDateFormat(pattern);
                try {
                    date = formatter.parse(source);
                    return date;
                } catch (Exception e) { }
            }
            ParsePosition pos = new ParsePosition(0);
            ISO8601Utils.parse(source, pos);
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", new Object[] { source }));
        }
        throw new RuntimeException(String.format("parser %s to Date fail", new Object[] { source }));
    }
}