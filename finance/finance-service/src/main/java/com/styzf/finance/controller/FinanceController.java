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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
	
	@Override
	@GetMapping
	public Response getFinance(Integer year, Integer month) {
		return SuccessResponseData.newInstance(financeService.getFinance(year, month));
	}

	@Override
	@PutMapping("addRemark")
	public Response addRemark(@RequestBody FinanceRemarkRequest request) {
		FinanceRemarkDTO dto = ConvertUtil.convert(request, FinanceRemarkDTO.class);
		financeService.addRemark(dto);
		return SuccessResponseData.newInstance();
	}
	
	@Override
	@PostMapping
	public Response addFinance(@Valid @RequestBody FinanceAddRequest request) {
		FinanceDTO dto = ConvertUtil.convert(request, FinanceDTO.class);
		financeService.baseInsertOrUpdate(dto);
		return SuccessResponseData.newInstance();
	}
}
