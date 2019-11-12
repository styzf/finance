package com.styzf.finance.mapper;

import com.styzf.finance.dto.user.MenuDTO;
import com.styzf.finance.po.Menu;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * <p>
 * 类别表 Mapper 接口
 * </p>
 *
 * @author yangzf
 * @since 2019-04-05
 */
@Repository
public interface MenuMapper extends Mapper<Menu> {
	List<MenuDTO> selectPermissionByUserId(String userId);
}
