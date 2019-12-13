package com.styzf.finance.service;

import com.styzf.core.common.base.BaseService;
import com.styzf.finance.dto.finance.FinanceDTO;
import com.styzf.finance.dto.finance.FinanceListData;
import com.styzf.finance.dto.finance.FinanceRemarkDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 类别表 服务类
 * </p>
 *
 * @author yangzf
 * @since 2019-04-05
 */
public interface IFinanceService extends BaseService<FinanceDTO> {
	
	/**
	 * 添加备注
	 * @param dto
	 */
	void addRemark(@RequestBody FinanceRemarkDTO dto);
	
	/**
	 * 根据年月查找数据
	 * @param year 年
	 * @param month 月
	 * @return 页面显示数据
	 */
	List<FinanceListData> getFinance(@PathVariable("year") Integer year, @PathVariable("year") Integer month);
}
