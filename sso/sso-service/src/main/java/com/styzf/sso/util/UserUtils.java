package com.styzf.sso.util;

import com.alibaba.fastjson.JSON;
import com.styzf.core.redis.RedisUtil;
import com.styzf.sso.constant.UserRedisKey;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 用户工具类，用于处理不敏感的用户数据
 * @author styzf
 * @date 2019-12-17
 */
@Log
@Component
public class UserUtils implements Serializable {
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Value("${auth.tokenValiditySeconds}")
	private long tokenValiditySeconds;
	
	public void setUserAuth(String userName, List<String> authList) {
		redisUtil.set(UserRedisKey.User.AUTH + userName,
				JSON.toJSONString(authList), Long.valueOf(tokenValiditySeconds), TimeUnit.SECONDS);
	}
	
	public List<String> getAuthList(String userName) {
		return redisUtil.getObject(UserRedisKey.User.AUTH + userName, List.class);
	}
	
}
