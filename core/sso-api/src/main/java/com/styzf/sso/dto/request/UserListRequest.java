package com.styzf.sso.dto.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * 用户列表请求参数
 * @author yangzf
 * @date 2020-06-16
 */
@Data
@ToString
public class UserListRequest {

    String userName;
}
