package com.styzf.core.common.response;

import lombok.Data;

/**
 * 返回实体类的父类
 * @author styzf
 * @date 2018年7月28日 
 *
 */
@Data
public class LoginResponse extends Response {
    
    public LoginResponse(Boolean success, String msg,String token) {
        super(success, msg);
        this.token = token;
    }
    
    public LoginResponse(Boolean success, String token) {
        super(success);
        this.token = token;
    }
    
    public LoginResponse(String token) {
        super();
        this.token = token;
    }
    
    private String token;
    
}
