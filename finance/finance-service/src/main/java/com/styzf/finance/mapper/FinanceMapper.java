package com.styzf.finance.mapper;

import com.styzf.finance.po.Finance;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * <p>
 * 账单表 Mapper 接口
 * </p>
 *
 * @author styzf
 * @since 2019-08-10
 */
@Repository
public interface FinanceMapper extends Mapper<Finance> {

}
