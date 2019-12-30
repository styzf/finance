package com.styzf.sso.mapper;

import com.styzf.sso.dto.user.RoleDTO;
import com.styzf.sso.po.MyUser;
import com.styzf.sso.po.Role;
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
public interface RoleMapper extends Mapper<Role> {
	
	/**
	 * 根据用户id进行查询
	 * @param userId
	 * @return
	 */
	List<RoleDTO> getByUserId(String userId);
}
