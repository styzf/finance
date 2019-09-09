package com.styzf.mybatis.constant;

import com.styzf.core.common.constant.CommonConstant;

public enum DeleteEnum {
	
	EXIST(Boolean.FALSE,"存在", CommonConstant.NO, CommonConstant.NUM_NO),
	DELETE(Boolean.TRUE,"已删除", CommonConstant.YES, CommonConstant.NUM_YES);
	
	private Boolean status;
	private String desc;
	private String deleteType;
	private Integer deleteInt;
	
	DeleteEnum(Boolean status,
	           String desc,
	           String deleteType,
	           Integer deleteInt) {
		this.status = status;
		this.desc = desc;
		this.deleteType = deleteType;
		this.deleteInt = deleteInt;
	}
	
	public Boolean getStatus() {
		return status;
	}
	
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getDeleteType() {
		return deleteType;
	}
	
	public void setDeleteType(String deleteType) {
		this.deleteType = deleteType;
	}
	
	public Integer getDeleteInt() {
		return deleteInt;
	}
	
	public void setDeleteInt(Integer deleteInt) {
		this.deleteInt = deleteInt;
	}
}
