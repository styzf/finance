package com.styzf.sso.service;

import com.styzf.sso.dto.UserDTO;
import com.styzf.sso.dto.user.UserExt;

/**
 * 用户服务接口说明
 * @author styzf
 * @date 2019-11-11
 */
public interface UserService {
	
	/**
	 * 根据用户名查询用户信息
	 * @param username 用户名
	 * @return 用户信息，仅用户的基础信息
	 */
	UserDTO findUserByUsername(String username);
	
	/**
	 * 根据用户名查询用户信息
	 * @param username 用户名
	 * @return 用户的基础信息，权限菜单，公司id
	 */
	UserExt getUserExt(String username);
}
