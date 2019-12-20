package com.styzf.finance.filter;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by mrt on 2018/5/21.
 */
@Data
@ToString
@NoArgsConstructor
public class AuthToken {
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
