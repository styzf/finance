package com.styzf.sso.client.filter;

import com.styzf.core.common.util.CookieUtil;
import com.styzf.core.redis.RedisUtil;
import com.styzf.sso.constant.UserRedisKey;
import com.styzf.sso.dto.AuthToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@WebFilter(urlPatterns = "/*", filterName = "httpFilter")
public class UserFilter implements Filter {

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private AuthSettings authSettings;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		UserHttpServletRequestWrapper req = new UserHttpServletRequestWrapper((HttpServletRequest) request);
		
		boolean hasAuth = checkAuth(req);
		if (hasAuth) {
			String token = CookieUtil.readCookie(req, "uid");
			
			if (StringUtils.isNotBlank(token)) {
				// 后面改为请求服务
				AuthToken authToken = redisUtil.getObject(UserRedisKey.User.TOKEN + token, AuthToken.class);
				if (Objects.nonNull(authToken)) {
					String jwt = authToken.getJwt_token();
					req.putHeader("Authorization", "Bearer " + jwt);
				}
			}
		}
		
		chain.doFilter(req, response);
	}
	
	private boolean checkAuth(UserHttpServletRequestWrapper req) {
		if (Objects.isNull(authSettings)) {
			return false;
		}
		String uri = req.getRequestURI();
		List<AuthUrl> authUrlList = authSettings.getAuthUrl();
		for (AuthUrl url: authUrlList) {
			if (uri.matches(url.getUrl()) && req.getMethod().equalsIgnoreCase(url.getType())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void destroy() {

	}
}
