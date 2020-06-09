package com.styzf.core.common.base;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * 页面参数对象
 * @author styzf
 *
 */
public class PageParams implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "第几页，默认第一页")
	private int page=1;
	@ApiModelProperty(value = "页大小，默认10")
	private int size=10;
	@ApiModelProperty(value = "排序字段，不传为默认排序")
	private String orderBy;
	
	public PageParams(){
		
	}
	
	public PageParams(Integer page, Integer size) {
		if (Objects.nonNull(page) && page > 0) {
			this.page = page;
		}
		if (Objects.nonNull(size) && size > 0) {
			this.size = size;
		}
	}
	
	public PageParams(int page, int size, String orderBy) {
	    this.page = page;
	    this.size = size;
	    this.orderBy = orderBy;
	}
	
	public int getPage() {
		return page;
	}
	
	public PageParams setPage(Integer page) {
		if (Objects.nonNull(page) && page > 0) {
			this.page = page;
		}
		return this;
	}
	
	public int getSize() {
		return size;
	}
	
	public PageParams setSize(Integer size) {
		if (Objects.nonNull(size) && size > 0) {
			this.size = size;
		}
		return this;
	}
	
	public String getOrderBy() {
		return orderBy;
	}
	
	public PageParams setOrderBy(String orderBy) {
		this.orderBy = orderBy;
		return this;
	}
}
