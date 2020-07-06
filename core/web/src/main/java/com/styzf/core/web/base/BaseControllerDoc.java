package com.styzf.core.web.base;

import com.styzf.core.common.base.BaseDTO;
import com.styzf.core.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.Serializable;
import java.util.List;

/**
 * @author styzf
 * @date 2020-07-03
 */
public interface BaseControllerDoc <D extends BaseDTO> {
    
    @ApiOperation("基础接口：根据id查找")
    public Response baseGetById(Serializable id);
    
    @ApiOperation("基础接口：列表查询")
    public Response baseList(D d);
    
    @ApiOperation("基础接口：分页查询")
    public Response basePage(D d);
    
    @ApiOperation("基础接口：新增数据")
    public Response baseAdd( D d);
    
    @ApiOperation("基础接口：修改数据")
    public Response baseUpdate(D d);
    
    @ApiOperation("基础接口：删除数据")
    public Response baseDeleteById(Serializable id);
    
    @ApiOperation("基础接口：批量删除数据")
    public Response remove(List<Long> ids);
}
