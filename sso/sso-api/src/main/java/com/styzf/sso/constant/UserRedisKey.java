package com.styzf.sso.constant;

/**
 * sso模块所有redis key管理
 * @author styzf
 * @date 2019-12-17
 */
public interface UserRedisKey {
	
	String PREFIX ="sso:user:";
	
	public interface User {
		/**
		 * 用户信息
		 */
		String INFO = PREFIX + "info:";
		
		/**
		 * 用户权限
		 */
		String AUTH = PREFIX + "auth:";
	}
}
