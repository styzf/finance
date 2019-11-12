package com.styzf.finance.dto.request;

import lombok.Data;
import lombok.ToString;

/**
 * @author yangzf
 * @date 2019-09-23
 */
@Data
@ToString
public class LoginRequest {

    String userName;
    String password;
    String verifycode;
    // 暂时无意义
    String type;
}
