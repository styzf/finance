package com.styzf.finance.doc;


import com.styzf.core.common.response.Response;
import com.styzf.finance.web.request.finance.FinanceAddRequest;
import com.styzf.finance.web.request.finance.FinanceRemarkRequest;
import com.styzf.finance.web.request.finance.FinanceUpdateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 账单表 前端控制器
 * </p>
 *
 * @author yangzf
 * @since 2019-08-10
 */
@Api(value="分类管理接口",description = "账单管理接口，提供页面的增、删、改、查")
public interface FinanceControllerDoc {
	
	@ApiOperation("账单查询分类列表")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
//			@ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
//	})
	public Response getFinance(Integer year, Integer month);
	
	@ApiOperation("添加账单")
	public Response addFinance(FinanceAddRequest request);
	
	@ApiOperation("添加备注")
	public Response addRemark(FinanceRemarkRequest request);
	
	@ApiOperation("账本数据查询")
	public Response getFinanceBook(Integer year, Integer month, Long categoryId);
	
	@ApiOperation("批量删除账单数据")
	public Response delete(List<Long> ids);
	
	@ApiOperation("修改账单数据")
	public Response updateFinance(FinanceUpdateRequest request);
}

