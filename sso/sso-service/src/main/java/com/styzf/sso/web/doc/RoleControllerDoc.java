package com.styzf.sso.web.doc;

import com.styzf.core.web.base.BaseControllerDoc;
import com.styzf.sso.dto.user.RoleDTO;
import io.swagger.annotations.Api;

/**
 * @author styzf
 * @date 2020-07-06
 */
@Api(value = "角色信息",description = "角色信息接口")
public interface RoleControllerDoc extends BaseControllerDoc<RoleDTO> {

}
