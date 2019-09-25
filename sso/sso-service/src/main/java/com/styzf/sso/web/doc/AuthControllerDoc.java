package com.styzf.sso.web.doc;

import com.styzf.core.common.response.LoginResponse;
import com.styzf.core.common.response.Response;
import com.styzf.finance.dto.request.LoginRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Administrator.
 */
@Api(value = "用户认证",description = "用户认证接口")
public interface AuthControllerDoc {
    @ApiOperation("登录")
    public Response login(LoginRequest loginRequest);

    @ApiOperation("退出")
    public Response logout();
}
