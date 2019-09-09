package com.styzf.finance.service;

import com.styzf.core.common.base.BaseService;
import com.styzf.finance.dto.finance.FinanceDTO;
import com.styzf.finance.dto.finance.FinanceListData;

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
	
	List<FinanceListData> getFinance(Integer year, Integer month);
}
