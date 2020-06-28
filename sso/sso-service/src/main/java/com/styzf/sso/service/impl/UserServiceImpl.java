package com.styzf.sso.service.impl;

import com.styzf.core.common.exception.BaseException;
import com.styzf.core.common.util.ConvertUtil;
import com.styzf.mybatis.base.BaseServiceImpl;
import com.styzf.sso.dto.request.UserSaveRequest;
import com.styzf.sso.dto.user.MenuDTO;
import com.styzf.sso.dto.user.RoleDTO;
import com.styzf.sso.dto.User;
import com.styzf.sso.dto.UserExt;
import com.styzf.sso.dto.user.UserDTO;
import com.styzf.sso.mapper.CompanyUserMapper;
import com.styzf.sso.mapper.MenuMapper;
import com.styzf.sso.mapper.UserMapper;
import com.styzf.sso.po.MyUser;
import com.styzf.sso.service.RoleService;
import com.styzf.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.weekend.Weekend;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
public class UserServiceImpl extends BaseServiceImpl<MyUser, UserDTO> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CompanyUserMapper companyUserMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleService roleService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    //根据账号查询xcUser信息
    @Override
    public User findUserByUsername(String username){
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        Weekend<MyUser> weekend = Weekend.of(MyUser.class);
        weekend.weekendCriteria().andEqualTo(MyUser::getUserName, username);
    
        List<MyUser> userList = userMapper.selectByExample(weekend);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        MyUser user = userList.get(0);
        return ConvertUtil.convert(user, User.class);
    }

    //根据账号查询用户信息
    @Override
    public UserExt getUserExt(String username){
        //根据账号查询xcUser信息
        User user = this.findUserByUsername(username);
        if(user == null){
            return null;
        }
        Long userId = user.getId();
    
        UserExt xcUserExt = ConvertUtil.convert(user, UserExt.class);
        //设置权限
        List<RoleDTO> roleList = roleService.getByUserId(userId);
        if (CollectionUtils.isEmpty(roleList)) {
            return xcUserExt;
        }
        List<Long> roleIdList = roleList.stream().map(RoleDTO::getId).collect(Collectors.toList());
    
        //查询用户所有权限
        List<MenuDTO> menuList = menuMapper.selectPermissionByUserId(roleIdList);
        xcUserExt.setPermissions(menuList);
        xcUserExt.setRoleList(roleList);

        //根据用户id查询用户所属公司id 公司相关的先不做处理
//        XcCompanyUser xcCompanyUser = xcCompanyUserRepository.findByUserId(userId);
//        //取到用户的公司id
//        String companyId = null;
//        if(xcCompanyUser!=null){
//            companyId = xcCompanyUser.getCompanyId();
//        }

        return xcUserExt;

    }
    
    @Override
    public void save(UserSaveRequest request) {
        String userName = request.getUserName();
        MyUser uniq = new MyUser();
        uniq.setUserName(userName);
        MyUser myUser = mapper.selectOne(uniq);
        if (Objects.nonNull(myUser)) {
            throw new BaseException("该用户名已注册");
        }
    
        String password = request.getPassword();
        String password2 = request.getPassword2();
        if (! password.equals(password2)) {
            throw new BaseException("两次输入的密码不一致");
        }
        password = passwordEncoder.encode(password);
        MyUser user = ConvertUtil.convert(request, clazzP);
        user.setPassword(password);
        mapper.insertSelective(user);
    }

}
