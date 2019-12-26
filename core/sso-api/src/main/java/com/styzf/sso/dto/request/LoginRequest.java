package com.styzf.sso.dto.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author yangzf
 * @date 2019-09-23
 */
@Data
@ToString
public class LoginRequest {

    @NotBlank(message = "用户名不能为空")
    String userName;
    @NotBlank(message = "密码不能为空")
    String password;
    String verifycode;
    // 暂时无意义
    String type;
}
