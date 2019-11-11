package com.styzf.core.common.exception;

/**
 * 用户自定义异常
 * @author styzf
 * @date 2019-11-07
 */
public class UserException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String code;
    
    private String errorKey;
    
    private String errorMsg;
    
    private Object[] params;
    
    private String ipAddress;
    
    private String traceId;

    public UserException(Exception e)
    {
      super(e);
    }
    
    public UserException(String errorKey) {
      super(errorKey);
      this.errorKey = errorKey;
    }
    
    public UserException(String errorKey, Throwable cause) {
      super(errorKey, cause);
      this.errorKey = errorKey;
    }
    
    public UserException(String errorKey, String errorMsg) {
      super(errorKey);
      this.errorKey = errorKey;
      this.errorMsg = errorMsg;
    }
    
    public UserException(String errorKey, String errorMsg, Throwable cause) {
      super(errorKey, cause);
      this.errorKey = errorKey;
      this.errorMsg = errorMsg;
    }
    
    public UserException(String traceId, String ipAddress, String errorMsg, Throwable cause) {
      super(cause);
      this.errorMsg = errorMsg;
      this.traceId = traceId;
      if (null != this.ipAddress) {
        this.ipAddress = (this.ipAddress + ";" + ipAddress);
      } else {
        this.ipAddress = ipAddress;
      }
    }
    
    public String getErrorKey() {
      return errorKey;
    }
    
    public void setErrorKey(String errorKey) {
      this.errorKey = errorKey;
    }
    
    public String getErrorMsg() {
      return errorMsg;
    }
    
    public void setErrorMsg(String errorMsg) {
      this.errorMsg = errorMsg;
    }
    
    public Object[] getParams() {
      return params;
    }
    
    public void setParams(Object[] params) {
      this.params = params;
    }
    
    public String getCode() {
      return code;
    }
    
    public void setCode(String code) {
      this.code = code;
    }
    
    public String getIpAddress() {
      return ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
      this.ipAddress = ipAddress;
    }
    
    public String getTraceId() {
      return traceId;
    }
    
    public void setTraceId(String traceId) {
      this.traceId = traceId;
    }
}
