package com.styzf.sso.web.controller.user;

import com.styzf.core.common.response.Response;
import com.styzf.core.common.response.SuccessResponseData;
import com.styzf.core.web.base.BaseController;
import com.styzf.sso.dto.request.LoginRequest;
import com.styzf.sso.dto.request.UserSaveRequest;
import com.styzf.sso.dto.user.UserDTO;
import com.styzf.sso.service.UserService;
import com.styzf.sso.web.doc.UserControllerDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author styzf
 * @date 2019-12-31
 **/
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<UserDTO> implements UserControllerDoc {

    @Autowired
    private UserService userService;
    
    @PostMapping("save")
     public Response save(@RequestBody @Valid UserSaveRequest request) {
         userService.save(request);
         return SuccessResponseData.newInstance();
     }
    
    @GetMapping("notices")
    public Response login(@RequestBody LoginRequest loginRequest) {
        return SuccessResponseData.newInstance();
    }
    
    @Override
    @GetMapping("currentUser")
    public Response currentUser(HttpServletRequest request) {
        return SuccessResponseData.newInstance("hello");
    }
    
}
