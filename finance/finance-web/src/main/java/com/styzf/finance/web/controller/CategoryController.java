package com.styzf.finance.web.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.styzf.core.common.base.PageParams;
import com.styzf.core.common.base.Pager;
import com.styzf.core.web.response.Response;
import com.styzf.core.web.response.SuccessResponseData;
import com.styzf.core.common.util.ConvertUtil;
import com.styzf.finance.dto.category.CategoryDTO;
import com.styzf.finance.dto.category.CategoryTree;
import com.styzf.finance.service.ICategoryService;
import com.styzf.finance.web.doc.CategoryControllerDoc;
import com.styzf.finance.web.request.category.CategoryAddRequest;
import com.styzf.finance.web.request.category.CategoryUpdateRequest;
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
	
	@Reference(check = false)
	private ICategoryService categoryService;
	
	@Override
	@GetMapping
	public Response getCategory(Integer page, Integer size, String name) {
		CategoryDTO dto = new CategoryDTO();
		dto.setName(name);
		Pager<CategoryDTO> pager = categoryService.basePage(dto, new PageParams(page, size));
		return SuccessResponseData.newInstance(pager);
	}
	
	@Override
	@GetMapping("/tree")
	public Response getCategoryTree(Long parentId) {
		CategoryTree categoryTree = categoryService.getCategoryTree(parentId);
		return SuccessResponseData.newInstance(categoryTree);
	}
	
	@Override
	@PostMapping
	public Response addCategory(@RequestBody @Valid CategoryAddRequest request) {
		CategoryDTO dto = ConvertUtil.convert(request, CategoryDTO.class);
		dto.setId(null);
		categoryService.baseInsertOrUpdate(dto);
		return SuccessResponseData.newInstance();
	}
	
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
	
	@Override
	@PutMapping
	public Response updateCategory(@RequestBody @Valid CategoryUpdateRequest request) {
		CategoryDTO dto = ConvertUtil.convert(request, CategoryDTO.class);
		categoryService.baseInsertOrUpdate(dto);
		return SuccessResponseData.newInstance();
	}
}

