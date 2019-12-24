package com.styzf.finance.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * 自定义request以实现修改请求头的功能
 * @author styzfSecurityContextHolder
 * @date 2019-12-20
 */
public class UserHttpServletRequestWrapper extends HttpServletRequestWrapper {
	/**
	 * Constructs a request object wrapping the given request.
	 *
	 * @param request The request to wrap
	 * @throws IllegalArgumentException if the request is null
	 */
	public UserHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		this.headers = new HashMap<>();
	}
	
	private Map<String, String> headers;
	
	public void putHeader(String name, String value) {
		this.headers.put(name, value);
	}
	
	@Override
	public String getHeader(String name) {
		String value = this.headers.get(name);
		if (value != null) {
			return value;
		}
		return ((HttpServletRequest) getRequest()).getHeader(name);
	}
	
	@Override
	public Enumeration<String> getHeaderNames() {
		Set<String> set = new HashSet<>(headers.keySet());
		Enumeration<String> enumeration = ((HttpServletRequest) getRequest()).getHeaderNames();
		while (enumeration.hasMoreElements()) {
			String name = enumeration.nextElement();
			set.add(name);
		}
		return Collections.enumeration(set);
	}
	
	@Override
	public Enumeration<String> getHeaders(String name) {
		String s = headers.get(name);
		Set<String> set = new HashSet<>();
		if (StringUtils.isNotBlank(s)) {
			set.add(s);
		}
		Enumeration<String> enumeration = ((HttpServletRequest) getRequest()).getHeaderNames();
		while (enumeration.hasMoreElements()) {
			String value = enumeration.nextElement();
			set.add(value);
		}
		return Collections.enumeration(set);
	}
}
