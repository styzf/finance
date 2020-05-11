package com.styzf.finance.controller;

import com.styzf.core.common.base.Pager;
import com.styzf.core.common.util.ConvertUtil;
import com.styzf.core.common.response.Response;
import com.styzf.core.common.response.SuccessResponseData;
import com.styzf.finance.dto.category.CategoryDTO;
import com.styzf.finance.dto.category.CategoryTree;
import com.styzf.finance.service.ICategoryService;
import com.styzf.finance.doc.CategoryControllerDoc;
import com.styzf.finance.web.request.category.CategoryAddRequest;
import com.styzf.finance.web.request.category.CategoryUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 类别表 前端控制器
 * </p>
 *
 * @author yangzf
 * @since 2019-04-05
 */
@RestController
@RequestMapping("/category")
public class CategoryController implements CategoryControllerDoc {
	
	@Autowired
	private ICategoryService categoryService;
	
	/**
	 * 分页查询分类列表
	 * @param page 页码
	 * @param size 页大小
	 * @param name 名称
	 * @param parentId 父id
	 * @param categoryKey 关键key
	 * @return 分类列表数据
	 */
	@Override
	@GetMapping
	public Response getCategory(Integer page, Integer size, String name, Long parentId, String categoryKey) {
		CategoryDTO dto = new CategoryDTO();
		dto.setName(name);
		dto.setParentId(parentId);
		dto.setPage(page);
		dto.setSize(size);
		dto.setCategoryKey(categoryKey);
		Pager<CategoryDTO> pager = categoryService.basePage(dto);
		return SuccessResponseData.newInstance(pager);
	}
	
	@PreAuthorize("hasAuthority('category_tree')")
	@Override
	@GetMapping("/tree")
	public Response getCategoryTree(Long parentId) {
		CategoryTree categoryTree = categoryService.getCategoryTree(parentId);
		return SuccessResponseData.newInstance(categoryTree);
	}
	
//	@PreAuthorize("hasAuthority('add_category')")
	@Override
	@PostMapping
	public Response addCategory(@RequestBody @Valid CategoryAddRequest request) {
		CategoryDTO dto = ConvertUtil.convert(request, CategoryDTO.class);
		dto.setId(null);
		categoryService.baseInsertOrUpdate(dto);
		return SuccessResponseData.newInstance();
	}
	
//	@PreAuthorize("hasAuthority('delete_category')")
	@Override
	@DeleteMapping
	public Response delete(@RequestBody List<Long> categoryIds) {
		if (CollectionUtils.isEmpty(categoryIds)) {
			return SuccessResponseData.newInstance();
		}
		categoryIds.stream().forEach(id -> {
			categoryService.baseDeleteById(id, null);
		});
		return SuccessResponseData.newInstance();
	}
	
//	@PreAuthorize("hasAuthority('update_category')")
	@Override
	@PutMapping
	public Response updateCategory(@RequestBody @Valid CategoryUpdateRequest request) {
		CategoryDTO dto = ConvertUtil.convert(request, CategoryDTO.class);
		categoryService.baseInsertOrUpdate(dto);
		return SuccessResponseData.newInstance();
	}
}

