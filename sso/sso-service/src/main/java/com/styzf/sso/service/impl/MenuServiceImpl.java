package com.styzf.sso.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.styzf.core.common.util.ConvertUtil;
import com.styzf.mybatis.base.BaseServiceImpl;
import com.styzf.sso.constant.UserConstant;
import com.styzf.sso.dto.user.MenuDTO;
import com.styzf.sso.dto.user.MenuTree;
import com.styzf.sso.po.Menu;
import com.styzf.sso.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author styzf
 * @date 2020-07-07
 **/
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu, MenuDTO> implements MenuService {
    
    @Override
    public MenuTree getMenuTree(Long parentId) {
        if (Objects.isNull(parentId)) {
            parentId = UserConstant.Menu.ROOT_ID;
        }
        MenuDTO root = baseGetById(parentId);
        MenuTree menuTree = ConvertUtil.convert(root, MenuTree.class);
    
        MenuDTO dto = new MenuDTO();
        dto.setPId(parentId);
        List<MenuDTO> childDtoList = baseList(dto);
        List<MenuTree> childList = ConvertUtil.convertList(childDtoList, MenuTree.class);
        if (CollectionUtil.isEmpty(childList)) {
            return menuTree;
        }
        
        menuTree.setChildList(childList);
        childList.forEach(this::getMenuTree);
        
        return menuTree;
    }
    
    /**
     * 循环查找子分类
     * @param tree
     * @return
     */
    private MenuTree getMenuTree(MenuTree tree) {
        MenuDTO dto = new MenuDTO();
        dto.setPId(tree.getId());
        List<MenuDTO> childDtoList = baseList(dto);
        if (CollectionUtil.isEmpty(childDtoList)) {
            return tree;
        }
        
        List<MenuTree> childList = ConvertUtil.convertList(childDtoList, MenuTree.class);
        tree.setChildList(childList);
        childList.forEach(this::getMenuTree);
        
        return tree;
    }
    
}
