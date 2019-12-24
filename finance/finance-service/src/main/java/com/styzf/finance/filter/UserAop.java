package com.styzf.finance.filter;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Slf4j
@Aspect
@Component
public class UserAop {
	
	@Before("execution(* org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(..)) && args(req,res,..)")
	public void beforeFilter(JoinPoint jp, ServletRequest req, ServletResponse res) {
		log.warn("切面拦截，能拦截到吗？" + req.getClass().getName());
	}
}
