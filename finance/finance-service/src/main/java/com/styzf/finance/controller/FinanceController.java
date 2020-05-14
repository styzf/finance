package com.styzf.finance.controller;


import com.styzf.core.common.util.ConvertUtil;
import com.styzf.core.common.response.Response;
import com.styzf.core.common.response.SuccessResponseData;
import com.styzf.finance.dto.finance.FinanceDTO;
import com.styzf.finance.dto.finance.FinanceRemarkDTO;
import com.styzf.finance.service.IFinanceService;
import com.styzf.finance.doc.FinanceControllerDoc;
import com.styzf.finance.web.request.finance.FinanceAddRequest;
import com.styzf.finance.web.request.finance.FinanceRemarkRequest;
import com.styzf.finance.web.request.finance.FinanceUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 类别表 前端控制器
 * </p>
 *
 * @author yangzf
 * @since 2019-04-05
 */
@RestController
@RequestMapping("/finance")
public class FinanceController implements FinanceControllerDoc {
	
	@Autowired
	private IFinanceService financeService;
	
	/**
	 * 财务管理页面数据
	 * @param year 要查询的年份
	 * @param month 要查询的月份
	 * @return 分类-记账管理下所有财务数据
	 */
	@Override
	@GetMapping
	public Response getFinance(Integer year, Integer month) {
		return SuccessResponseData.newInstance(financeService.getFinance(year, month));
	}
	
	/**
	 * 财务管理页面数据
	 * @param year 要查询的年份
	 * @param month 要查询的月份
	 * @return 分类-记账管理下所有财务数据
	 */
	@Override
	@GetMapping("book")
	public Response getFinanceBook(Integer year, Integer month, Long categoryId) {
		FinanceDTO dto = new FinanceDTO();
		dto.setYear(year).setMonth(month).setCategoryId(categoryId);
		return SuccessResponseData.newInstance(financeService.basePage(dto));
	}
	
	/**
	 * 添加备注数据
	 * @param request {@link FinanceRemarkRequest}
	 * @return
	 */
	@Override
	@PutMapping("addRemark")
	public Response addRemark(@RequestBody FinanceRemarkRequest request) {
		FinanceRemarkDTO dto = ConvertUtil.convert(request, FinanceRemarkDTO.class);
		financeService.addRemark(dto);
		return SuccessResponseData.newInstance();
	}
	
	/**
	 * 添加财务数据
	 * @param request {@link FinanceAddRequest}
	 * @return 成功正常返回，失败由拦截器处理
	 */
	@Override
	@PostMapping
	public Response addFinance(@Valid @RequestBody FinanceAddRequest request) {
		FinanceDTO dto = ConvertUtil.convert(request, FinanceDTO.class);
		
		financeService.baseInsertOrUpdate(dto);
		return SuccessResponseData.newInstance();
	}
	
	@Override
	@DeleteMapping
	public Response delete(@RequestBody List<Long> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			return SuccessResponseData.newInstance();
		}
		ids.stream().forEach(id -> {
			financeService.baseDeleteById(id, null);
		});
		return SuccessResponseData.newInstance();
	}
	
	@Override
	@PutMapping
	public Response updateFinance(@RequestBody @Valid FinanceUpdateRequest request) {
		FinanceDTO dto = ConvertUtil.convert(request, FinanceDTO.class);
		financeService.baseInsertOrUpdate(dto);
		return SuccessResponseData.newInstance();
	}
}

