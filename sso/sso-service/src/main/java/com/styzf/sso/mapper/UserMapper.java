package com.styzf.sso.mapper;

import com.styzf.sso.po.MyUser;
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
public interface UserMapper extends Mapper<MyUser> {

}
