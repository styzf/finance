package com.styzf.sso.web.controller.auth;

import com.styzf.core.common.exception.UserException;
import com.styzf.core.common.response.LoginResponse;
import com.styzf.core.common.response.Response;
import com.styzf.core.web.util.CookieUtil;
import com.styzf.sso.dto.AuthToken;
import com.styzf.sso.dto.request.LoginRequest;
import com.styzf.sso.service.impl.AuthService;
import com.styzf.sso.util.UserUtils;
import com.styzf.sso.web.doc.AuthControllerDoc;
import com.styzf.sso.dto.response.JwtResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
@RequestMapping
public class AuthController implements AuthControllerDoc {

    @Value("${auth.clientId}")
    private String clientId;
    @Value("${auth.clientSecret}")
    private String clientSecret;
    @Value("${auth.cookieDomain}")
    private String cookieDomain;
    @Value("${auth.cookieMaxAge}")
    private int cookieMaxAge;

    @Autowired
    private AuthService authService;
    
    @Autowired
    private UserUtils userUtils;
    
    @Override
    @PostMapping("/userlogin")
    public Response login(@RequestBody @Valid LoginRequest loginRequest) {
        if (StringUtils.isEmpty(loginRequest.getUserName())){
            throw new UserException("请输入用户名");
        }
        if (StringUtils.isEmpty(loginRequest.getPassword())){
            throw new UserException("请输入密码");
        }
        //账号
        String username = loginRequest.getUserName();
        //密码
        String password = loginRequest.getPassword();

        //申请令牌
        AuthToken authToken =  authService.login(username,password,clientId,clientSecret);
    
        //用户身份令牌
        String accessToken = authToken.getAccess_token();
        //将令牌存储到cookie
        this.saveCookie(accessToken);
    
        List<String> authList = userUtils.getAuthList(username);
        return new LoginResponse(authList);
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
    
	//取出cookie中的身份令牌
	private String getTokenFormCookie(HttpServletRequest request){
		Map<String, String> map = CookieUtil.readCookie(request, "uid");
		
		if (Objects.isNull(map)) {
			return null;
		}
		
		return map.get("uid");
	}
}
