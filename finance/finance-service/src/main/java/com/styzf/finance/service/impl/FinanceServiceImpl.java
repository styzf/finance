package com.styzf.finance.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.styzf.core.common.util.ConvertUtil;
import com.styzf.core.common.util.date.DateUtil;
import com.styzf.finance.dto.category.CategoryDTO;
import com.styzf.finance.dto.category.CategoryTree;
import com.styzf.finance.dto.finance.FinanceDTO;
import com.styzf.finance.dto.finance.FinanceListData;
import com.styzf.finance.dto.finance.FinanceRemarkDTO;
import com.styzf.finance.po.Finance;
import com.styzf.finance.service.ICategoryService;
import com.styzf.finance.service.IFinanceService;
import com.styzf.mybatis.base.BaseServiceImpl;
import com.styzf.mybatis.constant.DeleteEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 账单表 服务实现类
 * </p>
 *
 * @author yangzf
 * @since 2019-08-10
 */
@Service(interfaceClass = IFinanceService.class)
@Component
@Transactional
public class FinanceServiceImpl extends BaseServiceImpl<Finance, FinanceDTO> implements IFinanceService {
	
	@Autowired
	private ICategoryService categoryService;
	
	@Override
	public void addRemark(FinanceRemarkDTO dto) {
		String remark = dto.getRemark();
		FinanceDTO addDTO = ConvertUtil.convert(dto, FinanceDTO.class);
		addDTO.setRemark(null);
		List<FinanceDTO> list = baseList(addDTO);
		if (CollectionUtils.isEmpty(list)) {
			addDTO.setId(null);
			addDTO.setRemark(remark);
		} else {
			addDTO = list.get(0);
			addDTO.setRemark(remark);
		}
		baseInsertOrUpdate(addDTO);
	}
	
	@Override
	public List<FinanceListData> getFinance(Integer year, Integer month) {
		Calendar cal = Calendar.getInstance();
		if (null == year) {
			year = cal.get(Calendar.YEAR);
		}
		if (null == month) {
			month = cal.get(Calendar.MONTH )+1;
		}
		
		CategoryTree categoryTree = categoryService.getCategoryTree(1L);
		List<CategoryTree> list = categoryTree.getChildList();
		
		List<FinanceListData> dataList = new ArrayList<>();
		setData(list, dataList, year, month, false);
		return dataList;
	}
	
	/**
	 * 设置财务数据
	 * @param list 分类树列表
	 * @param dataList 保存数据的列表
	 * @param year 年
	 * @param month 月
	 * @param isChild 是否是子节点
	 */
	private void setData(List<CategoryTree> list, List<FinanceListData> dataList,
	                     Integer year, Integer month, boolean isChild) {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		for (CategoryTree tree: list) {
			FinanceListData data = ConvertUtil.convert(tree, FinanceListData.class);
			List<FinanceDTO> dtoList = getFinanceList(data.getId(), year, month);
			data.setData(dtoList);
			data.setChild(isChild);
			dataList.add(data);
			List<CategoryTree> childList = tree.getChildList();
			setData(childList, dataList, year, month, true);
		}
	}
	
	/**
	 * 获取财务数据
	 * @param categoryId 分类id
	 * @param year 年
	 * @param month 月
	 * @return 财务数据列表
	 */
	private List<FinanceDTO> getFinanceList(Long categoryId, Integer year, Integer month) {
		FinanceDTO dto = new FinanceDTO();
		dto.setCategoryId(categoryId).
		    setYear(year).
		    setMonth(month).
			setDeleteFlag(DeleteEnum.EXIST.getStatus());
		return baseList(dto);
	}
	
	@Override
	protected void insertOrUpdateBefore(FinanceDTO dto) {
		Weekend<Finance> weekend = Weekend.of(Finance.class);
		weekend.weekendCriteria().
				andEqualTo(Finance::getYear, dto.getYear()).
				andEqualTo(Finance::getMonth, dto.getMonth()).
				andEqualTo(Finance::getDay, dto.getDay()).
				andEqualTo(Finance::getCategoryId, dto.getCategoryId()).
				andEqualTo(Finance::getDeleteFlag, Boolean.FALSE);
		List<Finance> financeList = mapper.selectByExample(weekend);
		if (! CollectionUtils.isEmpty(financeList)) {
			Long id = financeList.get(0).getId();
			dto.setId(id);
		}
	}
}
