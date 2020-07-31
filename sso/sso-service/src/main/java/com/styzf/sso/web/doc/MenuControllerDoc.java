package com.styzf.sso.web.doc;

import com.styzf.core.common.response.Response;
import com.styzf.core.web.base.BaseControllerDoc;
import com.styzf.sso.dto.user.MenuDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author styzf
 * @date 2020-07-07
 */
@Api(value = "权限信息",description = "权限信息接口")
public interface MenuControllerDoc extends BaseControllerDoc<MenuDTO> {
    
    /**
     * 根据父分类id获取权限树列表
     * @param parentId 父id
     * @return 权限树
     */
    @ApiOperation("根据父分类id获取权限树列表")
    Response getMenuTree(Long parentId);
}
