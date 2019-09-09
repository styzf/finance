package com.styzf.finance.web.doc;


import com.styzf.core.web.response.Response;
import com.styzf.finance.web.request.category.CategoryAddRequest;
import com.styzf.finance.web.request.category.CategoryUpdateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * <p>
 * 类别表 前端控制器
 * </p>
 *
 * @author yangzf
 * @since 2019-04-05
 */
@Api(value="分类管理接口",description = "分类管理接口，提供页面的增、删、改、查")
public interface CategoryControllerDoc {
	
	/**
	 * 分页查询分类列表
	 * @param page
	 * @param size
	 * @return
	 */
	@ApiOperation("分页查询分类列表")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
//			@ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
//	})
	public Response getCategory(Integer page, Integer size,  String name);
	
	/**
	 * 根据父分类id获取分类树列表
	 * @param parentId
	 * @return
	 */
	@ApiOperation("根据父分类id获取分类树列表")
	public Response getCategoryTree(Long parentId);
	
	/**
	 * 添加分类
	 * @param request
	 * @return
	 */
	@ApiOperation("添加分类")
	public Response addCategory(CategoryAddRequest request);
	
	/**
	 * 删除分类
	 * @param categoryIds
	 * @return
	 */
	@ApiOperation("删除分类")
	public Response delete(List<Long> categoryIds);
	
	/**
	 * 修改分类
	 * @param request
	 * @return
	 */
	@ApiOperation("修改分类")
	public Response updateCategory(CategoryUpdateRequest request);
}

