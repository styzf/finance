package com.styzf.sso.service;

import com.styzf.core.common.base.BaseService;
import com.styzf.sso.dto.User;
import com.styzf.sso.dto.user.UserExt;
import com.styzf.sso.dto.user.UserDTO;

/**
 * 用户服务接口说明
 * @author styzf
 * @date 2019-11-11
 */
public interface UserService extends BaseService<UserDTO> {
	
	/**
	 * 根据用户名查询用户信息
	 * @param username 用户名
	 * @return 用户信息，仅用户的基础信息
	 */
	User findUserByUsername(String username);
	
	/**
	 * 根据用户名查询用户信息
	 * @param username 用户名
	 * @return 用户的基础信息，权限菜单，公司id
	 */
	UserExt getUserExt(String username);
}
