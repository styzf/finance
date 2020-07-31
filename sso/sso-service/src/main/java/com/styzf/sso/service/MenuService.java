package com.styzf.sso.service;

import com.styzf.core.common.base.BaseService;
import com.styzf.sso.dto.user.MenuDTO;
import com.styzf.sso.dto.user.MenuTree;

/**
 * 权限接口
 * @author styzf
 * @date 2020-07-07
 */
public interface MenuService extends BaseService<MenuDTO> {
    /**
     * 根据父id获取权限树
     * @param parentId
     * @return
     */
    MenuTree getMenuTree(Long parentId);
}
