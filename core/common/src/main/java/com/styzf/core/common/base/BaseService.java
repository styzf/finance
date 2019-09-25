package com.styzf.core.common.base;

import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author yangzf
 * @date 2019年3月9日
 * @param <D>
 */
public interface BaseService<D extends BaseDTO> {
	
	@GetMapping
	public List<D> baseList(D d);
	
	@PostMapping("page")
	public Pager<D> basePage(@RequestBody D d);
	
	/**
	 * TODO 最好把base的插入和更新再写一个，插入对id进行校验，更新对id进行设置
	 * @param d
	 * @return
	 */
	@PostMapping
	public D baseInsertOrUpdate(@RequestBody D d);
	
	/**
	 * 逻辑删除接口
	 * @param id 主键id
	 * @param d 用于逻辑删除时处理其他的变量
	 */
	@DeleteMapping("/{id}")
	public void baseDeleteById(@PathVariable("id") Serializable id, @RequestBody D d);
	
	@GetMapping("/{id}")
	public D baseGetById(@PathVariable("id") Serializable id);
}
