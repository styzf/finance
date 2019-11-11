package com.styzf.sso.dto.request;

import lombok.Data;
import lombok.ToString;

/**
 * @author yangzf
 * @date 2019-09-23
 */
@Data
@ToString
public class LoginRequest {

    String username;
    String password;
    String verifycode;

}
