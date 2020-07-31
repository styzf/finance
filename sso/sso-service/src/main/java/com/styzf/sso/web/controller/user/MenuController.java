package com.styzf.sso.web.controller.user;

import com.styzf.core.common.response.Response;
import com.styzf.core.common.response.SuccessResponseData;
import com.styzf.core.web.base.BaseController;
import com.styzf.sso.dto.user.MenuDTO;
import com.styzf.sso.dto.user.MenuTree;
import com.styzf.sso.service.MenuService;
import com.styzf.sso.web.doc.MenuControllerDoc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author styzf
 * @date 2020-07-07
 **/
@RestController
@RequestMapping("/user/menu")
public class MenuController extends BaseController<MenuDTO> implements MenuControllerDoc {
    
    @Override
    @GetMapping("/tree")
    public Response getMenuTree(Long parentId) {
        MenuTree menuTree = ((MenuService) service).getMenuTree(parentId);
        return SuccessResponseData.newInstance(menuTree);
    }
}
