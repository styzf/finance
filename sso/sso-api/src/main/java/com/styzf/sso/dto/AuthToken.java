package com.styzf.sso.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by mrt on 2018/5/21.
 */
@Data
@ToString
@NoArgsConstructor
public class AuthToken implements Serializable {
    /**
     * 访问token就是短令牌，用户身份令牌
     */
    private String access_token;
    
    /**
     * 刷新token
     */
    private String refresh_token;
    
    /**
     * jwt令牌
     */
    private String jwt_token;
}
