package com.styzf.core.common.response;

import com.styzf.core.common.util.IPUtils;
import lombok.Data;

/**
 * 发生异常返回给前端的实体类
 * @author styzf
 * @date 2018年7月28日 
 *
 */
@Data
public class ErrorResponseData extends Response {
    protected String errorCode;
    protected String stackMsg;
    protected String traceId;
    protected String providerIpAddress;
    protected String webIpAddress;
    
    private ErrorResponseData(String errorCode, String msg, String stackMsg) {
        super(Boolean.FALSE, msg);
        this.errorCode = errorCode;
        this.stackMsg = stackMsg;
    }
    
    public ErrorResponseData(String errorCode, String msg, String stackMsg, String traceId, String providerIpAddress) {
        super(Boolean.FALSE, msg);
        this.errorCode = errorCode;
        this.stackMsg = stackMsg;
        this.traceId = traceId;
        this.providerIpAddress = providerIpAddress;
        webIpAddress = IPUtils.getLocalIpAddress();
    }
    
    public ErrorResponseData() {
        super(Boolean.FALSE);
    }
    
    public static ErrorResponseData newInstance(String errorCode) {
        return new ErrorResponseData(errorCode, errorCode, null);
    }
    
    public static ErrorResponseData newInstance(String errorCode, String msg) {
        return new ErrorResponseData(errorCode, msg, null);
    }
    
    public static ErrorResponseData newInstance(String errorCode, String msg, String stackMsg) {
        return new ErrorResponseData(errorCode, msg, stackMsg);
    }
    
}
