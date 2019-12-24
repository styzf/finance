package com.styzf.finance.filter;

import com.styzf.core.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@WebFilter(urlPatterns = "/*", filterName = "httpFilter")
public class UserFilter implements Filter {
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.error(request.getClass().getName());
		UserHttpServletRequestWrapper req = new UserHttpServletRequestWrapper((HttpServletRequest) request);
		Cookie[] cookies = req.getCookies();
		String token = "";
		for (Cookie cookie : cookies) {
			if ("uid".equals(cookie.getName())) {
				token = cookie.getValue();
				break;
			}
		}
		if (StringUtils.isNotBlank(token)) {
			AuthToken authToken = redisUtil.getObject("user:auth:user_token:" + token, AuthToken.class);
			if (Objects.nonNull(authToken)) {
				String jwt = authToken.getJwt_token();
				req.putHeader("Authorization", "Bearer " + jwt);
			}
		}
		
		chain.doFilter(req, response);
	}
	
	@Override
	public void destroy() {
	
	}
}
