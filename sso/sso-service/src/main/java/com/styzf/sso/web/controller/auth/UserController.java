package com.styzf.sso.web.controller.auth;

import com.styzf.core.common.response.Response;
import com.styzf.core.common.response.SuccessResponseData;
import com.styzf.sso.dto.request.LoginRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
@RequestMapping("/")
public class UserController {

    @GetMapping("notices")
    public Response login(@RequestBody LoginRequest loginRequest) {
        return SuccessResponseData.newInstance();
    }
    
    @GetMapping("currentUser")
    public Response hello() {
        return SuccessResponseData.newInstance("hello");
    }

    @GetMapping("users")
    public Response users() {
        return SuccessResponseData.newInstance("users");
    }
}
