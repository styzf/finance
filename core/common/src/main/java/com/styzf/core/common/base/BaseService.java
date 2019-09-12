package com.styzf.core.common.base;

import java.io.Serializable;
import java.util.List;

/**
 * @author yangzf
 * @date 2019年3月9日
 * @param <D>
 */
public interface BaseService<D extends BaseDTO> {
	public List<D> baseList(D d);
	
	public Pager<D> basePage(D d, PageParams pageParams);
	
	/**
	 * TODO 最好把base的插入和更新再写一个，插入对id进行校验，更新对id进行设置
	 * @param d
	 * @return
	 */
	public D baseInsertOrUpdate(D d);
	
	/**
	 * 逻辑删除接口
	 * @param id 主键id
	 * @param d 用于逻辑删除时处理其他的变量
	 */
	public void baseDeleteById(Serializable id, D d);
	
	public D baseGetById(Serializable id);
}
