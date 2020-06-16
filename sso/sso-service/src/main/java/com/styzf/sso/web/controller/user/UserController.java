package com.styzf.sso.web.controller.user;

import com.styzf.core.common.response.Response;
import com.styzf.core.common.response.SuccessResponseData;
import com.styzf.sso.dto.request.LoginRequest;
import com.styzf.sso.dto.user.UserDTO;
import com.styzf.sso.service.UserService;
import com.styzf.sso.web.doc.UserControllerDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author styzf
 * @date 2019-12-31
 **/
@RestController
@RequestMapping("/user")
public class UserController implements UserControllerDoc {

    @Autowired
    private UserService userService;
    
    @GetMapping("notices")
    public Response login(@RequestBody LoginRequest loginRequest) {
        return SuccessResponseData.newInstance();
    }
    
    @Override
    @GetMapping("currentUser")
    public Response currentUser(HttpServletRequest request) {
        return SuccessResponseData.newInstance("hello");
    }
    
    @Override
    @GetMapping("list")
    public Response users(UserDTO request) {
        return SuccessResponseData.newInstance(userService.basePage(request));
        
    }
    @Override
    @DeleteMapping("remove")
    public Response remove(@RequestBody List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return SuccessResponseData.newInstance();
        }
        ids.stream().forEach(id -> {
            userService.baseDeleteById(id, null);
        });
        return SuccessResponseData.newInstance();
    }
    
    
}
