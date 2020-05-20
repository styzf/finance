package com.styzf.finance.service.impl;

import com.styzf.core.common.util.ConvertUtil;
import com.styzf.core.common.util.MyStringUtils;
import com.styzf.finance.constant.CategoryConstant;
import com.styzf.finance.dto.category.CategoryDTO;
import com.styzf.finance.dto.category.CategoryTree;
import com.styzf.finance.po.Category;
import com.styzf.finance.service.ICategoryService;
import com.styzf.mybatis.base.BaseServiceImpl;
import com.styzf.mybatis.constant.DeleteEnum;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 类别表 服务实现类
 * </p>
 *
 * @author yangzf
 * @since 2019-04-05
 */
@Component
@Transactional(rollbackFor = RuntimeException.class)
public class CategoryServiceImpl extends BaseServiceImpl<Category, CategoryDTO> implements ICategoryService {
	
	@Override
	public CategoryTree getCategoryTree(Long parentId) {
		if (Objects.isNull(parentId)) {
			parentId = CategoryConstant.ROOT_CATEGORY_ID;
		}
		CategoryDTO root = baseGetById(parentId);
		CategoryTree categoryTree = ConvertUtil.convert(root, CategoryTree.class);
		
		CategoryDTO dto = new CategoryDTO();
		dto.setParentId(parentId);
		List<CategoryDTO> childDtoList = baseList(dto);
		List<CategoryTree> childList = ConvertUtil.convertList(childDtoList, CategoryTree.class);
		if (CollectionUtils.isEmpty(childList)) {
			return categoryTree;
		}
		
		categoryTree.setChildList(childList);
		childList.stream().forEach(this::getCategoryTree);
		
		return categoryTree;
	}
	
	/**
	 * 循环查找子分类
	 * @param tree
	 * @return
	 */
	private CategoryTree getCategoryTree(CategoryTree tree) {
		CategoryDTO dto = new CategoryDTO();
		dto.setParentId(tree.getId());
		List<CategoryDTO> childDtoList = baseList(dto);
		if (CollectionUtils.isEmpty(childDtoList)) {
			return tree;
		}
		
		List<CategoryTree> childList = ConvertUtil.convertList(childDtoList, CategoryTree.class);
		tree.setChildList(childList);
		childList.stream().forEach(child -> {getCategoryTree(child);});
		
		return tree;
	}
	
	/**
	 * 创建前检测是否有指定父类id，没有的话将其放置在root之下
	 * @param category
	 */
	@Override
	protected void insertBefore(Category category) {
		Long parentId = category.getParentId();
		String parentKey = null;
		if (Objects.isNull(parentId)) {
			parentId = CategoryConstant.ROOT_CATEGORY_ID;
			parentKey = CategoryConstant.ROOT_CATEGORY_KEY;
		} else {
			Category po = mapper.selectByPrimaryKey(parentId);
			parentKey = po.getCategoryKey();
		}
		category.setParentId(parentId);
		category.setParentKey(parentKey);
		super.insertBefore(category);
	}
	
	// 分类名称模糊查询
	@Override
	protected Example selectPage(CategoryDTO categoryDTO) {
		if (Objects.isNull(categoryDTO) || MyStringUtils.isNull(categoryDTO.getName())) {
			return null;
		}
		Weekend<Category> weekend = Weekend.of(Category.class);
		weekend.weekendCriteria().
				andLike(Category::getName, "%" + categoryDTO.getName() + "%").
				andEqualTo(Category::getDeleteFlag, DeleteEnum.EXIST.getStatus());
		return weekend;
	}
	
}
