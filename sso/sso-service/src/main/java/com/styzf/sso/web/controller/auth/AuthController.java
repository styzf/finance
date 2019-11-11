package com.styzf.sso.web.controller.auth;

import com.styzf.core.common.exception.UserException;
import com.styzf.core.common.response.LoginResponse;
import com.styzf.core.common.response.Response;
import com.styzf.core.common.response.SuccessResponseData;
import com.styzf.core.web.util.CookieUtil;
import com.styzf.sso.dto.AuthToken;
import com.styzf.sso.dto.request.LoginRequest;
import com.styzf.sso.service.impl.AuthService;
import com.styzf.sso.web.doc.AuthControllerDoc;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
@RequestMapping("/")
public class AuthController implements AuthControllerDoc {

    @Value("${auth.clientId}")
    String clientId;
    @Value("${auth.clientSecret}")
    String clientSecret;
    @Value("${auth.cookieDomain}")
    String cookieDomain;
    @Value("${auth.cookieMaxAge}")
    int cookieMaxAge;

    @Autowired
    AuthService authService;

    @Override
    @PostMapping("/userlogin")
    public Response login(LoginRequest loginRequest) {
        if(Objects.isNull(loginRequest) || StringUtils.isEmpty(loginRequest.getUsername())){
            throw new UserException("");
        }
        if(Objects.isNull(loginRequest) || StringUtils.isEmpty(loginRequest.getPassword())){
            throw new UserException("");
        }
        //账号
        String username = loginRequest.getUsername();
        //密码
        String password = loginRequest.getPassword();

        //申请令牌
        AuthToken authToken =  authService.login(username,password,clientId,clientSecret);

        //用户身份令牌
        String access_token = authToken.getAccess_token();
        //将令牌存储到cookie
        this.saveCookie(access_token);

        return new LoginResponse(Boolean.TRUE,access_token);
    }

    //将令牌存储到cookie
    private void saveCookie(String token){

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response,cookieDomain,"/","uid",token,cookieMaxAge,false);

    }

    @Override
    public Response logout() {
        return null;
    }
    
    @GetMapping("hello")
    public Response hello() {
        return SuccessResponseData.newInstance("hello");
    }
}
