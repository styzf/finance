package com.styzf.core.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 页面参数对象
 * @author Gene
 *
 */
@Data
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
		if (null != page && page > 0) {
			this.page = page;
		}
		if (null != size && size > 0) {
			this.size = size;
		}
	}
	
	public PageParams(int page, int size, String orderBy) {
	    this.page = page;
	    this.size = size;
	    this.orderBy = orderBy;
	}

}
