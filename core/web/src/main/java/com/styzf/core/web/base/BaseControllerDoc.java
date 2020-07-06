package com.styzf.core.web.base;

import com.styzf.core.common.base.BaseDTO;
import com.styzf.core.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.Serializable;

/**
 * @author styzf
 * @date 2020-07-03
 */
@Api(value = "用户信息",description = "用户信息接口")
public interface BaseControllerDoc <D extends BaseDTO> {
    
    @ApiOperation("基础接口：根据id查找")
    public Response baseGetById(Serializable id);
}
