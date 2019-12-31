package com.styzf.sso.client.filter;

import com.styzf.core.common.config.LocaleMessageSource;
import com.styzf.core.common.exception.BaseException;
import com.styzf.core.common.response.ErrorResponseData;
import com.styzf.core.common.util.TraceContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

/**
 * 异常统一处理类
 * @author styzf
 * @date 2018年7月28日 
 *
 */
@Slf4j
@Order(-2)
@ControllerAdvice
public class SSOExceptionHandlerAdvice {
   
    @Autowired
    private LocaleMessageSource localeMessageSource;
    
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AccessDeniedException.class})
    @ResponseBody
    public ErrorResponseData handleBadRequest(HttpServletResponse rps, HttpServletRequest req, RuntimeException ex) {
        log.error("未授权", ex);
        String errorCode = ex.getMessage();
        String msg = "";
        String traceId = null;
        String ipAddress = null;
        try {
            msg = localeMessageSource.getMessage(errorCode);
        }
        catch (Exception localException1) {}
        
        if (StringUtils.isEmpty(msg)) {
            try {
                msg = localeMessageSource.getMessage("styzf.system.unauthorized");
            } catch (Exception e) {
                msg = "未授予权限,请联系管理员";
            }
        }
        if ((ex instanceof BaseException)) {
            traceId = TraceContext.getTraceId() + "";
            ipAddress = null != TraceContext.getSpans() ? StringUtils.join(TraceContext.getSpans().toArray()) : null;
        }
        ErrorResponseData data = new ErrorResponseData();
        data.setErrorCode(errorCode);
        data.setMsg(msg);
        data.setStackMsg(ExceptionUtils.getFullStackTrace(ex));
        data.setTraceId(traceId);
        data.setProviderIpAddress(ipAddress);
        return data;
    }
    
}

