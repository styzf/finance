package com.styzf.sso.web.controller.user;

import com.styzf.core.common.response.Response;
import com.styzf.core.common.response.SuccessResponseData;
import com.styzf.core.web.base.BaseController;
import com.styzf.sso.dto.request.LoginRequest;
import com.styzf.sso.dto.request.UserSaveRequest;
import com.styzf.sso.dto.user.RoleDTO;
import com.styzf.sso.dto.user.UserDTO;
import com.styzf.sso.po.Role;
import com.styzf.sso.service.UserService;
import com.styzf.sso.web.doc.RoleControllerDoc;
import com.styzf.sso.web.doc.UserControllerDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author styzf
 * @date 2020-07-06
 **/
@RestController
@RequestMapping("/user/role")
public class RoleController extends BaseController<RoleDTO> implements RoleControllerDoc {

}
