package com.styzf.sso.service.impl;

import com.styzf.core.common.util.Assert;
import com.styzf.mybatis.base.BaseServiceImpl;
import com.styzf.sso.dto.user.RoleDTO;
import com.styzf.sso.mapper.RoleMapper;
import com.styzf.sso.po.Role;
import com.styzf.sso.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;

/**
 * @author styzf
 * @date 2019-12-27
 **/
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, RoleDTO> implements RoleService {
    
    @Override
    public List<RoleDTO> getByUserId(String userId) {
        Assert.notBlank(userId, "查询角色用户id不能为空！");
        return ((RoleMapper) mapper).getByUserId(userId);
    }
}
