package com.styzf.sso.web.controller.user;

import com.styzf.core.web.base.BaseController;
import com.styzf.sso.dto.user.MenuDTO;
import com.styzf.sso.web.doc.MenuControllerDoc;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author styzf
 * @date 2020-07-07
 **/
@RestController
@RequestMapping("/user/menu")
public class MenuController extends BaseController<MenuDTO> implements MenuControllerDoc {

}
