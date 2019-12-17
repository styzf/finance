package com.styzf.core.common.response;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 返回实体类的父类
 * @author styzf
 * @date 2018年7月28日 
 *
 */
@Data
public class LoginResponse extends Response {
    
    public LoginResponse(Boolean success, String msg,String token, List<String> authList) {
        super(success, msg);
        this.token = token;
        this.authList = authList;
    }
    
    public LoginResponse(List<String> authList) {
        this(CollectionUtils.isNotEmpty(authList), null, null, authList);
    }
    
    public LoginResponse(String token) {
        this(StringUtils.isNoneBlank(token), null, token, null);
    }
    
    private String token;
    private List<String> authList;
}
