package com.styzf.finance.mapper;

import com.styzf.finance.po.Category;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * <p>
 * 类别表 Mapper 接口
 * </p>
 *
 * @author yangzf
 * @since 2019-04-05
 */
@Repository
public interface CategoryMapper extends Mapper<Category> {

}
