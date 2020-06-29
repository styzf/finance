package com.styzf.sso.dto.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author yangzf
 * @date 2020-06-23
 */
@Data
@ToString
public class UserSaveRequest {
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 手机号码
     */
    private String phone;
    
    /**
     * 用户名，暂无要求
     */
    private String name;
    
    @NotBlank(message = "帐户名不能为空")
    private String userName;
    
    @NotBlank(message = "密码不能为空")
    private String password;
    
    @NotBlank(message = "二次密码确认不能为空")
    private String password2;
    
    /**
     * 验证码，先不做
     */
    private String verifyCode;
    
    /**
     * 性别
     */
    private String sex;
}
