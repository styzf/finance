package com.styzf.sso.service.impl;

import com.styzf.sso.dto.user.MenuDTO;
import com.styzf.sso.dto.user.RoleDTO;
import com.styzf.sso.dto.UserExt;
import com.styzf.sso.service.UserService;
import com.styzf.sso.util.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 自定义UserDetailsService
 * @author styzf
 * @date 2019-09-23
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserUtils userUtils;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if(authentication==null){
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if(clientDetails!=null){
                //密码
                String clientSecret = clientDetails.getClientSecret();
                return new User(username,clientSecret,AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            }
        }
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        
        userService.findUserByUsername(username);
        UserExt userext = userService.getUserExt(username);
        if(userext == null){
            //返回空给spring security表示用户不存在
            return null;
        }
    
        //取出正确密码（hash值）
        String password = userext.getPassword();
        //用户权限，这里暂时使用静态数据，最终会从数据库读取
        //从数据库获取权限
        List<MenuDTO> permissions = userext.getPermissions();
        List<RoleDTO> roleList = userext.getRoleList();
        if(Objects.isNull(permissions)){
            permissions = new ArrayList<>();
        }
        if (Objects.isNull(roleList)) {
            roleList = new ArrayList<>();
        }
        List<String> userPermission = new ArrayList<>();
        permissions.forEach(item-> userPermission.add(item.getCode()));
        roleList.forEach(item-> userPermission.add(item.getRoleCode()));
        
        //使用静态的权限表示用户所拥有的权限
        String userPermissionString  = StringUtils.join(userPermission.toArray(), ",");
        UserJwt userDetails = new UserJwt(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(userPermissionString));
        userDetails.setId(userext.getId());
        userDetails.setUtype(userext.getUtype());//用户类型
        userDetails.setCompanyId(userext.getCompanyId());//所属企业
        userDetails.setName(userext.getName());//用户名称
        userDetails.setUserpic(userext.getUserpic());//用户头像
    
        userUtils.setUserAuth(username, userPermission);
        
        return userDetails;
    }
    
}
