package com.styzf.sso.service;

import com.styzf.core.common.base.BaseService;
import com.styzf.sso.dto.user.RoleDTO;
import com.styzf.sso.dto.user.UserDTO;
import com.styzf.sso.dto.user.UserExt;
import com.styzf.sso.po.Role;

import java.util.List;

/**
 * 角色接口
 * @author styzf
 * @date 2019-12-27
 */
public interface RoleService extends BaseService<RoleDTO> {
	
	/**
	 * 根据userId获取关联的角色列表
	 * @param userId
	 * @return
	 */
	List<RoleDTO> getByUserId(String userId);
}
