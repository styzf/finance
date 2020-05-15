package com.styzf.sso.web.doc;

import com.styzf.core.common.response.Response;
import com.styzf.sso.dto.request.LoginRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

/**
 * @author styzf
 * @date 2019-12-31
 */
@Api(value = "用户信息",description = "用户信息接口")
public interface UserControllerDoc {
    @ApiOperation("获取当前登录用户数据")
    public Response currentUser(HttpServletRequest request);

}
