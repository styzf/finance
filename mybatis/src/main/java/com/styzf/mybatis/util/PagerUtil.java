package com.styzf.mybatis.util;

import com.github.pagehelper.PageInfo;
import com.styzf.core.common.base.Pager;
import com.styzf.core.common.util.ConvertUtil;

import java.util.List;

/**
 * @author yangzf
 */
public class PagerUtil {
	
	public static <T,D> Pager<D> convertPage(PageInfo<T> pageInfo, Class<D> clazzD) {
		Pager<D> result = new Pager<>();
		List<D> list = ConvertUtil.convertList(pageInfo.getList(), clazzD);
		result.setContent(list);
		result.setPage(pageInfo.getPageNum());
		result.setSize(pageInfo.getSize());
		result.setTotalPages(pageInfo.getPages());
		result.setTotalElements(pageInfo.getTotal());
		return result;
	}
}
