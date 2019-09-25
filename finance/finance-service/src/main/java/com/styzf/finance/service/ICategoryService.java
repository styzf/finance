package com.styzf.finance.service;

import com.styzf.core.common.base.BaseService;
import com.styzf.finance.dto.category.CategoryDTO;
import com.styzf.finance.dto.category.CategoryTree;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 * 类别表 服务类
 * </p>
 *
 * @author yangzf
 * @since 2019-04-05
 */
public interface ICategoryService extends BaseService<CategoryDTO> {
	
	/**
	 * 根据父类id获取该节点下的所有分类树
	 * @param parentId
	 * @return
	 */
	CategoryTree getCategoryTree(@PathVariable("parentId") Long parentId);
}
